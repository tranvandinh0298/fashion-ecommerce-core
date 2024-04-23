package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageAndProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

@Mapper(componentModel = "spring", uses = {ImageMapper.class, ProductMapper.class})
public interface CategoryMapper {

    /**
     * Only Category
     * */
    @Mapping(source = "id", target = "categoryId")
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    Category mapToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromCategoryDTO(CategoryDTO categoryDTO, @MappingTarget Category category);

    /**
     * Category & Image
     * */
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "image", target = "imageDTO")
    CategoryWithImageDTO mapToCategoryAndImageDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    Category mapToCategory(CategoryWithImageDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    Category updateFromCategoryDTO(CategoryWithImageDTO categoryDTO, @MappingTarget Category category);

    /**
     * Category & Products
     * */
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "products", target = "productDTOs")
    CategoryWithProductsDTO mapToCategoryAndProductsDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "productDTOs", target = "products")
    Category mapToCategory(CategoryWithProductsDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.productDTOs", target = "category.products")
    void updateFromCategoryDTO(CategoryWithProductsDTO categoryDTO, @MappingTarget Category category);

    /**
     * Category & Image & Products
     * */
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "image", target = "imageDTO")
    @Mapping(source = "products", target = "productDTOs")
    CategoryWithImageAndProductsDTO mapToCategoryAndImageAndProductsDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    @Mapping(source = "productDTOs", target = "products")
    Category mapToCategory(CategoryWithImageAndProductsDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    @Mapping(source = "categoryDTO.productDTOs", target = "category.products")
    Category updateFromCategoryDTO(CategoryWithImageAndProductsDTO categoryDTO, @MappingTarget Category category);
}
