package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SkuDTO extends BaseDTO {
    public Integer skuId;
    public String code;
    public Integer quantity;
    public Double price;
}
