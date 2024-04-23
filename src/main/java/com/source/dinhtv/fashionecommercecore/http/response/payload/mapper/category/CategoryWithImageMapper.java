package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@InheritConfiguration(name = CategoryMapper.class)
//@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface CategoryWithImageMapper extends CategoryMapper{
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "image", target = "imageDTO")
    CategoryWithImageDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    Category mapToCategory(CategoryWithImageDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    Category updateFromCategoryDTO(CategoryWithImageDTO categoryDTO, @MappingTarget Category category);
}
