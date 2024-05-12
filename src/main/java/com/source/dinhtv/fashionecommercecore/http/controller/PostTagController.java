package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.tag.PostTagDTO;
import com.source.dinhtv.fashionecommercecore.service.PostTagService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/api/post-tags")
public class PostTagController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private PostTagService postTagService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllPostTags(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(postTagService.getAllPostTags(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllPostTagsWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllPostTagsWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(postTagService.getAllPostTagsWithoutPagination(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getPostTagById(@PathVariable int id) {
        return new ResponseEntity<>(postTagService.getPostTagById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPostTag(@Valid @RequestBody PostTagDTO postTagDTO) {
        return new ResponseEntity<>(postTagService.createPostTag(postTagDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updatePostTag(@PathVariable int id,@Valid @RequestBody PostTagDTO postTagDTO) {
        return new ResponseEntity<>(postTagService.updatePostTag(id, postTagDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeletePostTag(@PathVariable int id) {
        return new ResponseEntity<>(postTagService.softDeletePostTag(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deletePostTag(@PathVariable int id) {
        return new ResponseEntity<>(postTagService.deletePostTag(id), HttpStatus.ACCEPTED);
    }
}
