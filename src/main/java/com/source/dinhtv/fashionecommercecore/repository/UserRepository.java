package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.HashMap;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
