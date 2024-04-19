package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.service.AttributeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/attributes")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllAttributes(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) int limit) {
        return new ResponseEntity<>(attributeService.getAllAttributes(page, limit), HttpStatus.OK);
    }

    @GetMapping("/{attributeId}")
    public ResponseEntity<BaseResponse> getAttributeById(@PathVariable int attributeId) {
        return new ResponseEntity<>(attributeService.getAttributeById(attributeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAttribute(@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.createAttribute(attributeDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{attributeId}")
    public ResponseEntity<BaseResponse> updateAttribute(@PathVariable int attributeId,@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.updateAttribute(attributeId, attributeDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/{attributeId}")
    public ResponseEntity<BaseResponse> softDeleteAttribute(@PathVariable int attributeId) {
        return new ResponseEntity<>(attributeService.softDeleteAttribute(attributeId), HttpStatus.OK);
    }

    @DeleteMapping("/{attributeId}")
    public ResponseEntity<BaseResponse> deleteAttribute(@PathVariable int attributeId) {
        return new ResponseEntity<>(attributeService.deleteAttribute(attributeId), HttpStatus.ACCEPTED);
    }
}

