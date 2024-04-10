package com.source.dinhtv.fashionecommercecore.http.response.payload.dto;

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
public class ImageDTO {
    public Integer id;
    public String caption;
    public String address;
}
