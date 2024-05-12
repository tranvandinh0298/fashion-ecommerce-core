package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product;

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
public class ProductDTO extends BaseDTO {
    public Integer productId;
    public String title;
    public String slug;
    public String summary;
    public String description;
    public String photo;
    public Integer stock;
    public String size;
    public String condition;
    public String status;
    public Double price;
    public Double discount;
    public Integer isFeatured;
    public Integer categoryId;
    public Integer childCategoryId;
    public Integer brandId;
}
