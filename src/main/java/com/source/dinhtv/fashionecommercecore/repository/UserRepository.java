package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.HashMap;

public interface UserRepository extends JpaRepository<User, Integer> {
    User getById(Integer id);

    User getData(HashMap<String, String> filter);

    User save(User user);

    boolean updateById(Integer id, HashMap<String, String> updateData);

    boolean updateAll(HashMap<String, String> where, HashMap<String, String> updateData);
}
