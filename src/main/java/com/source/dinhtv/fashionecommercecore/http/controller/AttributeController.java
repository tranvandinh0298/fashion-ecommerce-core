package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.AttributeService;
import com.source.dinhtv.fashionecommercecore.service.CategoryService;
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
            @RequestParam(name = "page", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "limit", required = false, defaultValue = DEFAULT_PAGE_LIMIT) Integer limit) {
        return new ResponseEntity<>(attributeService.getAllAttributes(page, limit), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getAttributeById(@PathVariable Integer id) {
        return new ResponseEntity<>(attributeService.getAttributeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAttribute(@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.createAttribute(attributeDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateAttribute(@PathVariable Integer id,@Valid @RequestBody AttributeDTO attributeDTO) {
        return new ResponseEntity<>(attributeService.updateAttribute(id, attributeDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<BaseResponse> softDeleteAttribute(@PathVariable Integer id) {
        return new ResponseEntity<>(attributeService.softDeleteAttribute(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteAttribute(@PathVariable Integer id) {
        return new ResponseEntity<>(attributeService.deleteAttribute(id), HttpStatus.ACCEPTED);
    }
}

