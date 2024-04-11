package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.UserService;
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

    @GetMapping("/{email}")
    public ResponseEntity<BaseResponse> getUserByEmail(@PathVariable String email) {
        return new ResponseEntity<>(UserService.getUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public User createUser(@RequestBody User User) {
        return UserService.createUser(User);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User User) {
        return UserService.updateUser(id, User);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        UserService.deleteUser(id);
    }
}
