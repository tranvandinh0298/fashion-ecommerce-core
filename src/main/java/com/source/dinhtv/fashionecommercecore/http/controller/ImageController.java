package com.source.dinhtv.fashionecommercecore.http.controller;


import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping()
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<>(this.imageService.getAllImages(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Image> uploadFile(@RequestParam("file") MultipartFile file) {

        Image image = this.imageService.uploadSingleFile(file);

        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable Integer id) {

//        return new ResponseEntity<>(this.imageService.getImageById(id), HttpStatus.OK);

        return ResponseEntity.ok(this.imageService.getImageById(id));
    }

    @GetMapping("/caption/{name}")
    public ResponseEntity<Image> getImageByCaption(@PathVariable String name) {
        return new ResponseEntity<>(this.imageService.getImageByCaption(name), HttpStatus.OK);
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<Boolean> softDeleteImage(@PathVariable Integer id) {
        return new ResponseEntity<>(this.imageService.softDeleteImage(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteImage(@PathVariable Integer id) {
        return new ResponseEntity<>(this.imageService.deleteImage(id), HttpStatus.OK);
    }

}
