package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.PostTagController;
import com.source.dinhtv.fashionecommercecore.http.request.PostTagFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.tag.PostTagDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.tag.PostTagMapper;
import com.source.dinhtv.fashionecommercecore.model.PostTag;
import com.source.dinhtv.fashionecommercecore.repository.PostTagRepository;
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
public class PostTagService {
    @Autowired
    private PostTagRepository postTagRepository;
    @Autowired
    private PostTagMapper postTagMapper;

    public BaseResponse getAllPostTags(SearchRequest request) {
        new PostTagFilter(request).convertFilterKey();

        SearchSpecification<PostTag> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<PostTag> postTagPage = postTagRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<PostTagDTO>> PostTagEntities = postTagPage.stream().map(
                postTag -> EntityModel.of(
                        postTagMapper.mapToPostTagDTO(postTag),
                        linkTo(methodOn(PostTagController.class).getPostTagById(postTag.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<PostTagDTO>> pagedModel = getPagedModel(PostTagEntities, postTagPage.getNumber(), postTagPage.getSize(), postTagPage.getTotalElements(), postTagPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllPostTagsWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter postTagFilter = new PostTagFilter(request);
        postTagFilter.convertFilterKey();
        postTagFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<PostTag> specs = new DynamicalSpecification<>(request, postTagFilter.getAdditionalSpecs());

        // postTags
        List<PostTag> postTags = postTagRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<PostTagDTO>> PostTagEntities = postTags.stream().map(
                postTag -> EntityModel.of(
                        postTagMapper.mapToPostTagDTO(postTag),
                        linkTo(methodOn(PostTagController.class).getPostTagById(postTag.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(PostTagEntities);
    }

    public BaseResponse getPostTagById(int id) {
        PostTag existedPostTag = findByIdOrThrowEx(id);

        PostTagDTO postTagDTO = postTagMapper.mapToPostTagDTO(existedPostTag);

        Link allPostTagsLink = linkTo(methodOn(PostTagController.class).getAllPostTags(new SearchRequest())).withRel("allPostTags");

        EntityModel<PostTagDTO> postTagEntity = EntityModel.of(postTagDTO, allPostTagsLink);

        return new SuccessResponse(postTagEntity);
    }

    public BaseResponse createPostTag(PostTagDTO postTagDTO) {
        PostTag postTag = postTagMapper.mapToPostTag(postTagDTO);

        postTagRepository.save(postTag);

        return new SuccessResponse(postTagMapper.mapToPostTagDTO(postTag));
    }

    public BaseResponse updatePostTag(int id, PostTagDTO postTagDTO) {
        PostTag existedPostTag = findByIdOrThrowEx(id);

        postTagMapper.updateFromPostTagDTO(postTagDTO, existedPostTag);

        postTagRepository.save(existedPostTag);

        return new SuccessResponse(postTagMapper.mapToPostTagDTO(existedPostTag));
    }

    public BaseResponse softDeletePostTag(int id) {
        PostTag existedPostTag = findByIdOrThrowEx(id);

        existedPostTag.softDelete();

        postTagRepository.save(existedPostTag);

        return new SuccessResponse(true);
    }

    public BaseResponse deletePostTag(int id) {
        postTagRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected PostTag findByIdOrThrowEx(int id) {
        Specification<PostTag> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return postTagRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy postTag cần tìm với id: " + id));
    }
}
