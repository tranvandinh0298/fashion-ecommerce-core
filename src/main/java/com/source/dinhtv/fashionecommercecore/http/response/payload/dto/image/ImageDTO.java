package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ImageDTO {
    public Integer imageId;
    public String caption;
    public String address;
}
