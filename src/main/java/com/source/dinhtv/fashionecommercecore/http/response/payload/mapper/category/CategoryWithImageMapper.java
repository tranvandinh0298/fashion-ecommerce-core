package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface CategoryWithImageMapper extends CategoryMapper{
    @Mapping(source = "image", target = "imageDTO")
    CategoryWithImageDTO mapToCategoryDTO(Category category);

    @Mapping(source = "imageDTO", target = "image")
    Category mapToCategory(CategoryWithImageDTO categoryDTO);

    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    Category updateFromCategoryDTO(CategoryWithImageDTO categoryDTO, @MappingTarget Category category);
}
