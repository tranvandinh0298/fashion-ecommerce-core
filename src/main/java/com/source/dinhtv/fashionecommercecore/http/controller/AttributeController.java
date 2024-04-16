package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.AttributeService;
import com.source.dinhtv.fashionecommercecore.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_LIMIT;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("v1/api/attributes")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @GetMapping()
    public ResponseEntity<BaseResponse> getAllAttributes(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) Integer limit) {
        return new ResponseEntity<>(attributeService.getAllAttributes(page, limit), HttpStatus.OK);
    }

    @GetMapping("/attributeId")
    public ResponseEntity<BaseResponse> getAttributeById(@PathVariable Integer attributeId) {
        return new ResponseEntity<>(attributeService.getAttributeById(attributeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAttribute(@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.createAttribute(attributeDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/attributeId")
    public ResponseEntity<BaseResponse> updateAttribute(@PathVariable Integer attributeId,@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.updateAttribute(attributeId, attributeDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/attributeId")
    public ResponseEntity<BaseResponse> softDeleteAttribute(@PathVariable Integer attributeId) {
        return new ResponseEntity<>(attributeService.softDeleteAttribute(attributeId), HttpStatus.OK);
    }

    @DeleteMapping("/attributeId")
    public ResponseEntity<BaseResponse> deleteAttribute(@PathVariable Integer attributeId) {
        return new ResponseEntity<>(attributeService.deleteAttribute(attributeId), HttpStatus.ACCEPTED);
    }

    @GetMapping("/options")
    public ResponseEntity<BaseResponse> getAllAttributeOptions(
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) Integer limit) {
        return new ResponseEntity<>(attributeService.getAllAttributes(page, limit), HttpStatus.OK);
    }

    @GetMapping("/attributeId/options/{optionId}")
    public ResponseEntity<BaseResponse> getAttributeOptionById(@PathVariable Integer attributeId, @PathVariable Integer optionId) {
        return new ResponseEntity<>(attributeService.getAttributeById(attributeId), HttpStatus.OK);
    }

    @PostMapping("/attributeId/options")
    public ResponseEntity<BaseResponse> createAttributeOption(@Valid @RequestBody AttributeDTO attributeDTO, @Valid @RequestBody List<AttributeOptionDTO> attributeOptionDTO) {
        return new ResponseEntity<>(attributeService.createAttribute(attributeDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/attributeId/options/{optionId}")
    public ResponseEntity<BaseResponse> updateAttribute(@PathVariable Integer attributeId,@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.updateAttribute(attributeId, attributeDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/attributeId")
    public ResponseEntity<BaseResponse> softDeleteAttribute(@PathVariable Integer attributeId) {
        return new ResponseEntity<>(attributeService.softDeleteAttribute(attributeId), HttpStatus.OK);
    }

    @DeleteMapping("/attributeId")
    public ResponseEntity<BaseResponse> deleteAttribute(@PathVariable Integer attributeId) {
        return new ResponseEntity<>(attributeService.deleteAttribute(attributeId), HttpStatus.ACCEPTED);
    }
}

