package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
import com.source.dinhtv.fashionecommercecore.model.Brand;
import com.source.dinhtv.fashionecommercecore.model.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    public Integer status;
    public Double price;
    public Double discount;
    public Integer isFeatured;
    public Integer categoryId;
    public Integer childCategoryId;
    public Integer brandId;
}
