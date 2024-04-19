package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.service.CollectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/collections")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCollections(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        return new ResponseEntity<>(collectionService.getAllCollections(page, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCollectionById(@PathVariable int id) {
        return new ResponseEntity<>(collectionService.getCollectionById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCollection(@Valid @RequestBody CollectionDTO collectionDTO) {
        return new ResponseEntity<>(collectionService.createCollection(collectionDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCollection(@PathVariable int id,@Valid @RequestBody CollectionDTO collectionDTO) {
        return new ResponseEntity<>(collectionService.updateCollection(id, collectionDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<BaseResponse> softDeleteCollection(@PathVariable int id) {
        return new ResponseEntity<>(collectionService.softDeleteCollection(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCollection(@PathVariable int id) {
        return new ResponseEntity<>(collectionService.deleteCollection(id), HttpStatus.ACCEPTED);
    }
}
