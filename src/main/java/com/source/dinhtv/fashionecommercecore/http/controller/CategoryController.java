package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithParentCategoryDTO;
import com.source.dinhtv.fashionecommercecore.service.CategoryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/categories")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCategories(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllCategories -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(categoryService.getAllCategories(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllCategoriesWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllCategoriesWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(categoryService.getAllCategories(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCategoryById(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCategory(@PathVariable int id,@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeleteCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.softDeleteCategory(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.ACCEPTED);
    }
}
