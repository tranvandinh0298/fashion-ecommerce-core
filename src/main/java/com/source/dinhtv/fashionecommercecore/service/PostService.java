package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.PostController;
import com.source.dinhtv.fashionecommercecore.http.request.PostFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.PostDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.PostWithCategoryAndUserAndTagDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.PostMapper;
import com.source.dinhtv.fashionecommercecore.model.Post;
import com.source.dinhtv.fashionecommercecore.repository.PostRepository;
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
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;

    public BaseResponse getAllPosts(SearchRequest request) {
        new PostFilter(request).convertFilterKey();

        SearchSpecification<Post> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Post> postPage = postRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<PostWithCategoryAndUserAndTagDTO>> PostEntities = postPage.stream().map(
                post -> EntityModel.of(
                        postMapper.mapToPostWithCategoryAndUserAndTagDTO(post),
                        linkTo(methodOn(PostController.class).getPostById(post.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<PostWithCategoryAndUserAndTagDTO>> pagedModel = getPagedModel(PostEntities, postPage.getNumber(), postPage.getSize(), postPage.getTotalElements(), postPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllPostsWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter postFilter = new PostFilter(request);
        postFilter.convertFilterKey();
        postFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Post> specs = new DynamicalSpecification<>(request, postFilter.getAdditionalSpecs());

        // posts
        List<Post> posts = postRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<PostWithCategoryAndUserAndTagDTO>> PostEntities = posts.stream().map(
                post -> EntityModel.of(
                        postMapper.mapToPostWithCategoryAndUserAndTagDTO(post),
                        linkTo(methodOn(PostController.class).getPostById(post.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(PostEntities);
    }

    public BaseResponse getPostById(int id) {
        Post existedPost = findByIdOrThrowEx(id);

        PostWithCategoryAndUserAndTagDTO postDTO = postMapper.mapToPostWithCategoryAndUserAndTagDTO(existedPost);

        Link allPostsLink = linkTo(methodOn(PostController.class).getAllPosts(new SearchRequest())).withRel("allPosts");

        EntityModel<PostWithCategoryAndUserAndTagDTO> postEntity = EntityModel.of(postDTO, allPostsLink);

        return new SuccessResponse(postEntity);
    }

    public BaseResponse createPost(PostDTO postDTO) {
        Post post = postMapper.mapToPost(postDTO);

        postRepository.save(post);

        return new SuccessResponse(postMapper.mapToPostDTO(post));
    }

    public BaseResponse updatePost(int id, PostDTO postDTO) {
        Post existedPost = findByIdOrThrowEx(id);

        postMapper.updateFromPostDTO(postDTO, existedPost);

        postRepository.save(existedPost);

        return new SuccessResponse(postMapper.mapToPostDTO(existedPost));
    }

    public BaseResponse softDeletePost(int id) {
        Post existedPost = findByIdOrThrowEx(id);

        existedPost.softDelete();

        postRepository.save(existedPost);

        return new SuccessResponse(true);
    }

    public BaseResponse deletePost(int id) {
        postRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected Post findByIdOrThrowEx(int id) {
        Specification<Post> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return postRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy post cần tìm với id: " + id));
    }
}
