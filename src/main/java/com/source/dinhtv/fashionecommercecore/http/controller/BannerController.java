package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.banner.BannerDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.service.BannerService;
import com.source.dinhtv.fashionecommercecore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/banners")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllBanners(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        return new ResponseEntity<>(bannerService.getAllBanners(page, limit), HttpStatus.OK);
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
