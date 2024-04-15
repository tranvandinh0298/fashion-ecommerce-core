package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.Image;
import com.source.dinhtv.fashionecommercecore.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface CategoryMapper {
    @Mapping(source = "image", target = "imageDTO")
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(source = "imageDTO", target = "image")
    Category mapToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryDTO.imageDTO", target = "category.image")
    Category updateCategoryFromCategoryDTO(CategoryDTO categoryDTO, @MappingTarget Category category);
}
