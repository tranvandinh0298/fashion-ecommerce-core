package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.UserMapper;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.repository.UserRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.combineSpecs;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.isNonDeletedRecord;
import static com.source.dinhtv.fashionecommercecore.repository.specification.UserSpecification.hasEmail;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public BaseResponse getAllUsers() {
        Specification<User> specs = combineSpecs(List.of(
           isNonDeletedRecord()
        ));
        List<User> users = userRepository.findAll(specs);

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy bất cứ người dùng nào");
        }

        List<UserDTO> userDTOs = users.stream().map(UserMapper.MAPPER::userToUserDTO).toList();

        return new SuccessResponse(userDTOs);
    }

    public BaseResponse getUserById(Integer id) {
        Specification<User> specs = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        User user = this.userRepository.findOne(specs).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng nào với id: " + id));

        UserDTO userDTO = UserMapper.MAPPER.userToUserDTO(user);

        return new SuccessResponse(userDTO);
    }

    public BaseResponse getUserByEmail(String email) {
        Specification<User> specs = combineSpecs(List.of(
                isNonDeletedRecord(),
                hasEmail(email)
        ));
        User user = this.userRepository.findOne(specs).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng nào với email: " + email));

        UserDTO userDTO = UserMapper.MAPPER.userToUserDTO(user);

        return new SuccessResponse(userDTO);
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
