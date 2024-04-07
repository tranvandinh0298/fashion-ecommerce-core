package com.source.dinhtv.fashionecommercecore.http.controller;

import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/users")
public class UserController {
    @Autowired
    private UserService UserService;

    @GetMapping("")
    public List<User> getAllUsers() {
        return UserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return UserService.getUserById(id);
    }

    @GetMapping("/{email}")
    public List<User> getUserByEmail(@PathVariable String email) {
        return UserService.getAllUsers();
//        return UserService.getUserByName(email);
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
