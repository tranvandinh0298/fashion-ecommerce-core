package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {
    public Integer userId;
    public String name;
    public String email;
    public Date emailVerifiedAt;
}
