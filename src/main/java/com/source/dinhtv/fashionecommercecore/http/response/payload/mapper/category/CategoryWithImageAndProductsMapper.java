package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageAndProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, ProductMapper.class})
public interface CategoryWithImageAndProductsMapper {
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "image", target = "imageDTO")
    @Mapping(source = "products", target = "productDTOs")
    CategoryWithImageAndProductsDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    @Mapping(source = "productDTOs", target = "products")
    Category mapToCategory(CategoryWithImageAndProductsDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    @Mapping(source = "categoryDTO.productDTOs", target = "category.products")
    Category updateFromCategoryDTO(CategoryWithImageAndProductsDTO categoryDTO, @MappingTarget Category category);
}
