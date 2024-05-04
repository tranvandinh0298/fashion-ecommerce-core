package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.exception.GlobalExceptionHandler;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.banner.BannerDTO;
import com.source.dinhtv.fashionecommercecore.service.BannerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("v1/api/banners")
public class BannerController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private BannerService bannerService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllBanners(@RequestBody(required = false) SearchRequest request) {
        logger.info("incoming request:" + request.toString());

        if (Objects.isNull(request)) {
            request = new SearchRequest();
        }

        return new ResponseEntity<>(bannerService.getAllBanners(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getBannerById(@PathVariable int id) {
        return new ResponseEntity<>(bannerService.getBannerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createBanner(@Valid @RequestBody BannerDTO bannerDTO) {
        return new ResponseEntity<>(bannerService.createBanner(bannerDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateBanner(@PathVariable int id,@Valid @RequestBody BannerDTO bannerDTO) {
        return new ResponseEntity<>(bannerService.updateBanner(id, bannerDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<BaseResponse> softDeleteBanner(@PathVariable int id) {
        return new ResponseEntity<>(bannerService.softDeleteBanner(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteBanner(@PathVariable int id) {
        return new ResponseEntity<>(bannerService.deleteBanner(id), HttpStatus.ACCEPTED);
    }
}
