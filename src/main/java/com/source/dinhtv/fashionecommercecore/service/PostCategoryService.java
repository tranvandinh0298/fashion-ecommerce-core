package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.PostCategoryController;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.PostCategoryFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.category.PostCategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.category.PostCategoryMapper;
import com.source.dinhtv.fashionecommercecore.model.PostCategory;
import com.source.dinhtv.fashionecommercecore.repository.PostCategoryRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.DynamicalSpecification;
import com.source.dinhtv.fashionecommercecore.repository.specification.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PostCategoryService {
    @Autowired
    private PostCategoryRepository postCategoryRepository;
    @Autowired
    private PostCategoryMapper postCategoryMapper;

    public BaseResponse getAllPostCategories(SearchRequest request) {
        new PostCategoryFilter(request).convertFilterKey();

        SearchSpecification<PostCategory> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<PostCategory> postCategoryPage = postCategoryRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<PostCategoryDTO>> PostCategoryEntities = postCategoryPage.stream().map(
                postCategory -> EntityModel.of(
                        postCategoryMapper.mapToPostCategoryDTO(postCategory),
                        linkTo(methodOn(PostCategoryController.class).getPostCategoryById(postCategory.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<PostCategoryDTO>> pagedModel = getPagedModel(PostCategoryEntities, postCategoryPage.getNumber(), postCategoryPage.getSize(), postCategoryPage.getTotalElements(), postCategoryPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllPostCategoriesWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter postCategoryFilter = new PostCategoryFilter(request);
        postCategoryFilter.convertFilterKey();
        postCategoryFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<PostCategory> specs = new DynamicalSpecification<>(request, postCategoryFilter.getAdditionalSpecs());

        // postCategories
        List<PostCategory> postCategories = postCategoryRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<PostCategoryDTO>> PostCategoryEntities = postCategories.stream().map(
                postCategory -> EntityModel.of(
                        postCategoryMapper.mapToPostCategoryDTO(postCategory),
                        linkTo(methodOn(PostCategoryController.class).getPostCategoryById(postCategory.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(PostCategoryEntities);
    }

    public BaseResponse getPostCategoryById(int id) {
        PostCategory existedPostCategory = findByIdOrThrowEx(id);

        PostCategoryDTO postCategoryDTO = postCategoryMapper.mapToPostCategoryDTO(existedPostCategory);

        Link allPostCategoriesLink = linkTo(methodOn(PostCategoryController.class).getAllPostCategories(new SearchRequest())).withRel("allPostCategories");

        EntityModel<PostCategoryDTO> postCategoryEntity = EntityModel.of(postCategoryDTO, allPostCategoriesLink);

        return new SuccessResponse(postCategoryEntity);
    }

    public BaseResponse createPostCategory(PostCategoryDTO postCategoryDTO) {
        PostCategory postCategory = postCategoryMapper.mapToPostCategory(postCategoryDTO);

        postCategoryRepository.save(postCategory);

        return new SuccessResponse(postCategoryMapper.mapToPostCategoryDTO(postCategory));
    }

    public BaseResponse updatePostCategory(int id, PostCategoryDTO postCategoryDTO) {
        PostCategory existedPostCategory = findByIdOrThrowEx(id);

        postCategoryMapper.updateFromPostCategoryDTO(postCategoryDTO, existedPostCategory);

        postCategoryRepository.save(existedPostCategory);

        return new SuccessResponse(postCategoryMapper.mapToPostCategoryDTO(existedPostCategory));
    }

    public BaseResponse softDeletePostCategory(int id) {
        PostCategory existedPostCategory = findByIdOrThrowEx(id);

        existedPostCategory.softDelete();

        postCategoryRepository.save(existedPostCategory);

        return new SuccessResponse(true);
    }

    public BaseResponse deletePostCategory(int id) {
        postCategoryRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected PostCategory findByIdOrThrowEx(int id) {
        Specification<PostCategory> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return postCategoryRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy postCategory cần tìm với id: " + id));
    }
}
