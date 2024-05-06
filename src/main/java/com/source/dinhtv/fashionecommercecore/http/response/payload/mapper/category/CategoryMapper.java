package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithParentCategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;

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
     * Category & Parent Category
     * */
    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "parentCategory", target = "parentCategoryDTO")
    CategoryWithParentCategoryDTO mapToCategoryWithParentCategoryDTO(Category category);

    @Mapping(source = "categoryId", target = "id")
    @Mapping(source = "parentCategoryDTO", target = "parentCategory")
    Category mapToCategory(CategoryWithParentCategoryDTO categoryWithParentCategoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryWithParentCategoryDTO.parentCategoryDTO", target = "category.parentCategory")
    void updateFromCategoryWithParentCategoryDTO(CategoryWithParentCategoryDTO categoryWithParentCategoryDTO, @MappingTarget Category category);
}
