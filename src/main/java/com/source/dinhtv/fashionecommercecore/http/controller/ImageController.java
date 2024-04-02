package com.source.dinhtv.fashionecommercecore.http.controller;


import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("v1/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping()
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) {

        Image image = this.imageService.uploadSingleFile(file);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }
}
