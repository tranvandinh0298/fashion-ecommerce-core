package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryWithProductsMapper {
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "products", target = "productDTOs")
    CategoryWithProductsDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "productDTOs", target = "products")
    Category mapToCategory(CategoryWithProductsDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.productDTOs", target = "category.products")
    Category updateFromCategoryDTO(CategoryWithProductsDTO categoryDTO, @MappingTarget Category category);
}
