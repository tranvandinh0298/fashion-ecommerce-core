package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.service.AttributeOptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/attributes/{attributeId}/options")
public class AttributeOptionController {
    @Autowired
    private AttributeOptionService attributeOptionService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllAttributeOptions(@PathVariable int attributeId,
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        return new ResponseEntity<>(attributeOptionService.getAllAttributeOptions(attributeId, page, limit), HttpStatus.OK);
    }

    @GetMapping("/{optionId}")
    public ResponseEntity<BaseResponse> getAttributeOptionById(@PathVariable int attributeId, @PathVariable int optionId) {
        return new ResponseEntity<>(attributeOptionService.getAttributeOptionById(attributeId, optionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAttributeOption(@PathVariable int attributeId, @Valid @RequestBody AttributeOptionDTO optionDTO) {
        return new ResponseEntity<>(attributeOptionService.createAttributeOption(attributeId, optionDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{optionId}")
    public ResponseEntity<BaseResponse> updateAttributeOption(@PathVariable int attributeId, @PathVariable int optionId, @Valid @RequestBody AttributeOptionDTO optionDTO) {
        return new ResponseEntity<>(attributeOptionService.updateAttributeOption(attributeId, optionId, optionDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/{optionId}")
    public ResponseEntity<BaseResponse> softDeleteAttributeOption(@PathVariable int attributeId, @PathVariable int optionId) {
        return new ResponseEntity<>(attributeOptionService.softDeleteAttributeOption(attributeId, optionId), HttpStatus.OK);
    }

    @DeleteMapping("/{optionId}")
    public ResponseEntity<BaseResponse> deleteAttribute(@PathVariable int attributeId, @PathVariable int optionId) {
        return new ResponseEntity<>(attributeOptionService.deleteAttributeOption(attributeId, optionId), HttpStatus.ACCEPTED);
    }
}
