package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.service.ProductService;
import com.source.dinhtv.fashionecommercecore.service.ProductService;
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
@RequestMapping("v1/api/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllProducts(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(productService.getAllProducts(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllProductsWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllProductsWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(productService.getAllProductsWithoutPagination(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getProductById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateProduct(@PathVariable int id,@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeleteProduct(@PathVariable int id) {
        return new ResponseEntity<>(productService.softDeleteProduct(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteProduct(@PathVariable int id) {
        return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.ACCEPTED);
    }
}
