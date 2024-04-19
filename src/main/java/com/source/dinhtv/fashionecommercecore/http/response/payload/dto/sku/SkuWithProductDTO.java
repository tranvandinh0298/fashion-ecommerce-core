package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
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
public class SkuWithProductDTO extends SkuDTO {
    public ProductDTO productDTO;
}
