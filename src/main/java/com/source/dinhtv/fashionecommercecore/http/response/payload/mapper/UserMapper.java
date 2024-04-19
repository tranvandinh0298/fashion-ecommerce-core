package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.user.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOtoUser(UserDTO userDTO);

    @Mapping(target = "userDTO.id", ignore = true)
    @Mapping(target = "userDTO.email", ignore = true)
    @Mapping(target = "userDTO.emailVerifiedAt", ignore = true)
    User updateUserFromUserDTO(UserDTO userDTO, @MappingTarget User user);
}
