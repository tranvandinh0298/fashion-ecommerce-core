package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCategories(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) Integer limit) {
        return new ResponseEntity<>(this.categoryService.getAllCategories(page, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCategory(@PathVariable Integer id,@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<BaseResponse> softDeleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.softDeleteImage(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.ACCEPTED);
    }
}
