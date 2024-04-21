package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageAndProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface CategoryWithImageAndProductsMapper extends CategoryWithImageMapper {
    @Mapping(source = "products", target = "productDTOs")
    CategoryWithImageAndProductsDTO mapToCategoryDTO(Category category);

    @Mapping(source = "productDTOs", target = "products")
    Category mapToCategory(CategoryWithImageAndProductsDTO categoryDTO);

    @Mapping(source = "categoryDTO.productDTOs", target = "category.products")
    Category updateFromCategoryDTO(CategoryWithImageAndProductsDTO categoryDTO, @MappingTarget Category category);
}
