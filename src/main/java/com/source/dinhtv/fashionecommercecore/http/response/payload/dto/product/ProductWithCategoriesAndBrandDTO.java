package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.brand.BrandDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
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
public class ProductWithCategoriesAndBrandDTO extends ProductDTO {
    public CategoryDTO categoryDTO;
    public CategoryDTO childCategoryDTO;
    public BrandDTO brandDTO;
}
