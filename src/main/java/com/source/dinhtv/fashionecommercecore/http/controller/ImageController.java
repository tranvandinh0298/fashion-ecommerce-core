package com.source.dinhtv.fashionecommercecore.http.controller;


import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.service.ImageService;
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
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) Integer limit) {
        return new ResponseEntity<>(this.imageService.getAllImages(page, limit), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(this.imageService.uploadSingleFile(file), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getImage(@PathVariable Integer id) {
        return ResponseEntity.ok(this.imageService.getImageById(id));
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<BaseResponse> softDeleteImage(@PathVariable Integer id) {
        return new ResponseEntity<>(this.imageService.softDeleteImage(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteImage(@PathVariable Integer id) {
        return new ResponseEntity<>(this.imageService.deleteImage(id), HttpStatus.OK);
    }

}
