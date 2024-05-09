package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.brand.BrandDTO;
import com.source.dinhtv.fashionecommercecore.service.BrandService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/api/brands")
public class BrandController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private BrandService brandService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllBrands(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(brandService.getAllBrands(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllBrandsWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllBrandsWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(brandService.getAllBrandsWithoutPagination(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBrandById(@PathVariable int id) {
        return new ResponseEntity<>(brandService.getBrandById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.createBrand(brandDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBrand(@PathVariable int id,@Valid @RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(brandService.updateBrand(id, brandDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeleteBrand(@PathVariable int id) {
        return new ResponseEntity<>(brandService.softDeleteBrand(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBrand(@PathVariable int id) {
        return new ResponseEntity<>(brandService.deleteBrand(id), HttpStatus.ACCEPTED);
    }
}
