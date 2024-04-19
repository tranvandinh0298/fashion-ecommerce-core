package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface CategoryMapper {
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "image", target = "imageDTO")
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    Category mapToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    Category updateCategoryFromCategoryDTO(CategoryDTO categoryDTO, @MappingTarget Category category);
}
