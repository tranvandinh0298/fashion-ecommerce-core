package com.source.dinhtv.fashionecommercecore.http.response.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BaseDTO {
    public Date createdAt;
    public Date updatedAt;
}
