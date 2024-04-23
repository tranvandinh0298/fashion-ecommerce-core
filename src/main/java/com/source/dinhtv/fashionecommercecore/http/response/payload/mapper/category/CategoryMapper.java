package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;
import org.springframework.context.annotation.Primary;

@MapperConfig(componentModel = "spring",mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG, uses = {ProductMapper.class, ImageMapper.class})
//@Mapper(componentModel = "spring")
//@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface CategoryMapper {
    @Mapping(source = "id", target = "categoryId")
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    Category mapToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    Category updateFromCategoryDTO(CategoryDTO categoryDTO, @MappingTarget Category category);
}
