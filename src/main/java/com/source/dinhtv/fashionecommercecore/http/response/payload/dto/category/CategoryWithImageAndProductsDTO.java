package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
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
public class CategoryWithImageAndProductsDTO extends CategoryWithImageDTO{
    public List<ProductDTO> productDTOs;
}
