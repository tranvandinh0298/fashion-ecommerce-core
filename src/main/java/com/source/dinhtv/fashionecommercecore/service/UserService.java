package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User createUser(User User) {
        return userRepository.save(User);
    }

    public User updateUser(Integer id, User User) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        User.setId(id);
        return userRepository.save(User);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
