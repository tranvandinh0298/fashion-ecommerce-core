package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ImageDTO extends BaseDTO {
    public Integer imageId;
    public String caption;
    public String address;
}
