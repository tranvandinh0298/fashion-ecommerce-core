package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithParentCategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // Mapping for sole Category
    @Mapping(source = "id", target = "categoryId")
    CategoryDTO mapToCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    Category mapToCategory(CategoryDTO categoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    @Mapping(target = "parentCategory", ignore = true)
    void updateCategory(Category incomingCategory, @MappingTarget Category existedCategory);

    // Mapping for Category with Parent Category
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "parentCategory", target = "parentCategoryDTO", qualifiedByName = "mapParentCategory")
    CategoryWithParentCategoryDTO mapToCategoryWithParentCategoryDTO(Category category);

    @Named("mapParentCategory")
    default CategoryDTO mapParentCategory(Category parentCategory) {
        return !Objects.isNull(parentCategory) ? mapToCategoryDTO(parentCategory) : null;
    }
}
