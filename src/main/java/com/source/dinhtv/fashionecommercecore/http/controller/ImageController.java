package com.source.dinhtv.fashionecommercecore.http.controller;


import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
import com.source.dinhtv.fashionecommercecore.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllImages(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        return new ResponseEntity<>(this.imageService.getAllImages(page, limit), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAllImagesWithoutPagination() {
        return new ResponseEntity<>(this.imageService.getAllImagesWithoutPagination(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> createImage(@Valid @RequestBody ImageDTO imageDTO) {
        return new ResponseEntity<>(this.imageService.createImage(imageDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getImage(@PathVariable int id) {
        return ResponseEntity.ok(this.imageService.getImageById(id));
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<BaseResponse> softDeleteImage(@PathVariable int id) {
        return new ResponseEntity<>(this.imageService.softDeleteImage(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteImage(@PathVariable int id) {
        return new ResponseEntity<>(this.imageService.deleteImage(id), HttpStatus.OK);
    }

}
