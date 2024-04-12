package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/users")
public class UserController {
    @Autowired
    private UserService UserService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getAllUsers() {
        return new ResponseEntity<>(this.UserService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(UserService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(UserService.createUser(user), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable Integer id,@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(UserService.updateUser(id, userDTO), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable Integer id) {
        return new ResponseEntity<>(UserService.deleteUser(id), HttpStatus.ACCEPTED);
    }
}
