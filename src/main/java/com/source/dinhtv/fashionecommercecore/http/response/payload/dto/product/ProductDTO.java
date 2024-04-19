package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
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
public class ProductDTO {
    public Integer productId;

    public String name;

    public String slug;

    public String code;

    public String description;

    public Integer status;

    public List<CategoryDTO> categoryDTOs;

    public List<SkuDTO> skuDTOs;

    public List<ImageDTO> imageDTOs;
}
