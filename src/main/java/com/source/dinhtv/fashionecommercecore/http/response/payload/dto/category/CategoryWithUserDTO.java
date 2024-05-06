package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CategoryWithUserDTO extends CategoryDTO{
    public UserDTO userDTO;
}
