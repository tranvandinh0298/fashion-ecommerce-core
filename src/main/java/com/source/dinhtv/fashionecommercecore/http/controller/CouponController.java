package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.coupon.CouponDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.coupon.CouponDTO;
import com.source.dinhtv.fashionecommercecore.service.CouponService;
import com.source.dinhtv.fashionecommercecore.service.CouponService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/api/coupons")
public class CouponController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private CouponService couponService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllCoupons(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(couponService.getAllCoupons(request), HttpStatus.OK);
    }

    @GetMapping("/without-pagination")
    public ResponseEntity<BaseResponse> getAllCouponsWithoutPagination(@RequestBody(required = false) SearchRequest request) {
        logger.info("getAllCouponsWithoutPagination -> incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(couponService.getAllCouponsWithoutPagination(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getCouponById(@PathVariable int id) {
        return new ResponseEntity<>(couponService.getCouponById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        return new ResponseEntity<>(couponService.createCoupon(couponDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCoupon(@PathVariable int id,@Valid @RequestBody CouponDTO couponDTO) {
        return new ResponseEntity<>(couponService.updateCoupon(id, couponDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}/soft-delete")
    public ResponseEntity<BaseResponse> softDeleteCoupon(@PathVariable int id) {
        return new ResponseEntity<>(couponService.softDeleteCoupon(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCoupon(@PathVariable int id) {
        return new ResponseEntity<>(couponService.deleteCoupon(id), HttpStatus.ACCEPTED);
    }
}
