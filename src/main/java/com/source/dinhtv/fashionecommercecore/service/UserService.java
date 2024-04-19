package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceAlreadyExists;
import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.user.UserDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.UserMapper;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public BaseResponse getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy bất cứ người dùng nào");
        }

        List<UserDTO> userDTOs = users.stream().map(UserMapper.MAPPER::userToUserDTO).toList();

        return new SuccessResponse(userDTOs);
    }

    public BaseResponse getUserById(Integer id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng nào với id: " + id));

        UserDTO userDTO = UserMapper.MAPPER.userToUserDTO(user);

        return new SuccessResponse(userDTO);
    }

    public BaseResponse createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExists("Email: "+ user.getEmail()+ " đã tồn tại");
        }

        userRepository.save(user);

        UserDTO userDTO = UserMapper.MAPPER.userToUserDTO(user);

        return new SuccessResponse(userDTO);
    }

    public BaseResponse updateUser(Integer id, UserDTO userDTO) {
        User existingUser = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng nào với id: " + id));

        UserMapper.MAPPER.updateUserFromUserDTO(userDTO, existingUser);

        User updatedUser = this.userRepository.save(existingUser);

        return new SuccessResponse(UserMapper.MAPPER.userToUserDTO(updatedUser));
    }

    public BaseResponse deleteUser(Integer id) {

        userRepository.deleteById(id);

        return new SuccessResponse();
    }
}
