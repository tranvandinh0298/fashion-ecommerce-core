package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.brand;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
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
public class BrandDTO extends BaseDTO {
    public Integer brandId;
    public String title;
    public String slug;
    public String status;
}