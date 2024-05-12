package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.category.PostCategoryDTO;
import com.source.dinhtv.fashionecommercecore.service.PostCategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/api/post-categories")
public class PostCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private PostCategoryService postCategoryService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllPostCategories(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(postCategoryService.getAllPostCategories(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllPostCategoriesWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllPostCategoriesWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(postCategoryService.getAllPostCategoriesWithoutPagination(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getPostCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(postCategoryService.getPostCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPostCategory(@Valid @RequestBody PostCategoryDTO postCategoryDTO) {
        return new ResponseEntity<>(postCategoryService.createPostCategory(postCategoryDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updatePostCategory(@PathVariable int id,@Valid @RequestBody PostCategoryDTO postCategoryDTO) {
        return new ResponseEntity<>(postCategoryService.updatePostCategory(id, postCategoryDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeletePostCategory(@PathVariable int id) {
        return new ResponseEntity<>(postCategoryService.softDeletePostCategory(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deletePostCategory(@PathVariable int id) {
        return new ResponseEntity<>(postCategoryService.deletePostCategory(id), HttpStatus.ACCEPTED);
    }
}
