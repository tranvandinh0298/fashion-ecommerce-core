package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class CategoryController {
    @Autowired
    private CategoryService CategoryService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllUsers() {
        return new ResponseEntity<>(CategoryService.getAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(CategoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCategory(@Valid @RequestBody User user) {
        return new ResponseEntity<>(CategoryService.createCategory(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCategory(@PathVariable Integer id,@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(CategoryService.updateCategory(id, userDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCategory(@PathVariable Integer id) {
        return new ResponseEntity<>(CategoryService.deleteCategory(id), HttpStatus.ACCEPTED);
    }
}
