package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithParentCategoryDTO;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;

import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IMAGE_URL;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // Mapping for sole Category
    @Named("mapToCategoryDTO")
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

    @AfterMapping
    default void addImageBaseUrl(Category category, @MappingTarget CategoryDTO categoryDTO ) {
        String baseUrl = IMAGE_URL;
        if (category.getPhoto() != null) {
            categoryDTO.setPhoto(baseUrl + category.getPhoto());
        }
    }
}
