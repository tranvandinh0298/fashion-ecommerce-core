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
    @Named("mapToCategoryDTO")
    @Mapping(source = "id", target = "categoryId")
    CategoryDTO mapToCategoryDTO(Category category);

    @Named("mapToCategory")
    @Mapping(source = "categoryId", target = "id")
    Category mapToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromCategoryDTO(CategoryDTO categoryDTO, @MappingTarget Category category);

    /**
     * Category & Image
     * */
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "image", target = "imageDTO", qualifiedByName = "mapToImageDTO")
    CategoryWithImageDTO mapToCategoryAndImageDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "imageDTO", target = "image", qualifiedByName = "mapToImage")
    Category mapToCategory(CategoryWithImageDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image", qualifiedByName = "mapToImage")
    Category updateFromCategoryDTO(CategoryWithImageDTO categoryDTO, @MappingTarget Category category);

//    /**
//     * Category & Products
//     * */
//    @Mapping(source = "id", target = "categoryId")
//    @Mapping(source = "products", target = "productDTOs", qualifiedByName = "mapToProductDTO")
//    CategoryWithProductsDTO mapToCategoryAndProductsDTO(Category category);
//
//    @Mapping(source = "categoryId", target = "id")
//    @Mapping(source = "productDTOs", target = "products", qualifiedByName = "mapToProduct")
//    Category mapToCategory(CategoryWithProductsDTO categoryDTO);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "categoryDTO.productDTOs", target = "category.products", qualifiedByName = "mapToProduct")
//    void updateFromCategoryDTO(CategoryWithProductsDTO categoryDTO, @MappingTarget Category category);
//
//    /**
//     * Category & Image & Products
//     * */
//    @Mapping(source = "id", target = "categoryId")
//    @Mapping(source = "image", target = "imageDTO", qualifiedByName = "mapToImageDTO")
//    @Mapping(source = "products", target = "productDTOs", qualifiedByName = "mapToProductDTO")
//    CategoryWithImageAndProductsDTO mapToCategoryAndImageAndProductsDTO(Category category);
//
//    @Mapping(source = "categoryId", target = "id")
//    @Mapping(source = "imageDTO", target = "image", qualifiedByName = "mapToImage")
//    @Mapping(source = "productDTOs", target = "products", qualifiedByName = "mapToProduct")
//    Category mapToCategory(CategoryWithImageAndProductsDTO categoryDTO);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "categoryDTO.imageDTO", target = "category.image", qualifiedByName = "mapToImage")
//    @Mapping(source = "categoryDTO.productDTOs", target = "category.products", qualifiedByName = "mapToProduct")
//    Category updateFromCategoryDTO(CategoryWithImageAndProductsDTO categoryDTO, @MappingTarget Category category);
}
