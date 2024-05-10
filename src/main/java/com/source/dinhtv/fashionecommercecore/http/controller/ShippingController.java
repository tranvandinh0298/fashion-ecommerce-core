package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.shipping.ShippingDTO;
import com.source.dinhtv.fashionecommercecore.service.ShippingService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/api/shippings")
public class ShippingController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private ShippingService shippingService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllShippings(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(shippingService.getAllShippings(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllShippingsWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllShippingsWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(shippingService.getAllShippingsWithoutPagination(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getShippingById(@PathVariable int id) {
        return new ResponseEntity<>(shippingService.getShippingById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createShipping(@Valid @RequestBody ShippingDTO shippingDTO) {
        return new ResponseEntity<>(shippingService.createShipping(shippingDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateShipping(@PathVariable int id,@Valid @RequestBody ShippingDTO shippingDTO) {
        return new ResponseEntity<>(shippingService.updateShipping(id, shippingDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeleteShipping(@PathVariable int id) {
        return new ResponseEntity<>(shippingService.softDeleteShipping(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteShipping(@PathVariable int id) {
        return new ResponseEntity<>(shippingService.deleteShipping(id), HttpStatus.ACCEPTED);
    }
}
