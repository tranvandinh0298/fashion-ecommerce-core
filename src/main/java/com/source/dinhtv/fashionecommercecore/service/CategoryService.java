package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.BannerController;
import com.source.dinhtv.fashionecommercecore.http.controller.CategoryController;
import com.source.dinhtv.fashionecommercecore.http.request.BannerFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.CategoryFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryWithParentCategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.repository.CategoryRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.DynamicalSpecification;
import com.source.dinhtv.fashionecommercecore.repository.specification.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IS_NOT_PARENT_CATEGORY;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IS_PARENT_CATEGORY;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public BaseResponse getAllCategories(SearchRequest request) {
        // filter object
        BaseFilter categoryFilter = new CategoryFilter(request);
        categoryFilter.convertFilterKey();
        categoryFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Category> specs = new DynamicalSpecification<>(request, categoryFilter.getAdditionalSpecs());

        // page with categories
        Page<Category> categoryPage = categoryRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        // convert entities to DTOs
        List<EntityModel<CategoryWithParentCategoryDTO>> CategoryEntities = categoryPage.stream().map(
                category -> EntityModel.of(
                        categoryMapper.mapToCategoryWithParentCategoryDTO(category),
                        linkTo(methodOn(CategoryController.class).getCategoryById(category.getId())).withSelfRel())
        ).toList();

        // page with categoryDTOs
        PagedModel<EntityModel<CategoryWithParentCategoryDTO>> pagedModel = getPagedModel(CategoryEntities, categoryPage.getNumber(), categoryPage.getSize(), categoryPage.getTotalElements(), categoryPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllCategoriesWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter categoryFilter = new CategoryFilter(request);
        categoryFilter.convertFilterKey();
        categoryFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Category> specs = new DynamicalSpecification<>(request, categoryFilter.getAdditionalSpecs());

        // categories
        List<Category> categories = categoryRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<CategoryWithParentCategoryDTO>> CategoryEntities = categories.stream().map(
                category -> EntityModel.of(
                        categoryMapper.mapToCategoryWithParentCategoryDTO(category),
                        linkTo(methodOn(CategoryController.class).getCategoryById(category.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(CategoryEntities);
    }

    public BaseResponse getCategoryById(int id) {
        Category existedCategory = findByIdOrThrowEx(id);

        CategoryDTO categoryDTO = categoryMapper.mapToCategoryWithParentCategoryDTO(existedCategory);

        Link allCategoriesLink = linkTo(methodOn(CategoryController.class).getAllCategories(new SearchRequest())).withRel("allCategories");

        EntityModel<CategoryDTO> categoryEntity = EntityModel.of(categoryDTO, allCategoriesLink);

        return new SuccessResponse(categoryEntity);
    }

    public BaseResponse createCategory(CategoryDTO categoryDTO) {
        Category incomingCategory = categoryMapper.mapToCategory(categoryDTO);

        if (incomingCategory.getIsParent().equals(IS_NOT_PARENT_CATEGORY) && categoryDTO.getParentCategoryId() != null) {
            Category parentCategory = findByIdOrThrowEx(categoryDTO.getParentCategoryId());

            incomingCategory.setParentCategory(parentCategory);
        }

        categoryRepository.save(incomingCategory);

        return new SuccessResponse(categoryMapper.mapToCategoryDTO(incomingCategory));
    }

    public BaseResponse updateCategory(int id, CategoryDTO categoryDTO) {
        Category existedCategory = findByIdOrThrowEx(id);

        Category incomingCategory = categoryMapper.mapToCategory(categoryDTO);

        if (incomingCategory.getIsParent().equals(IS_NOT_PARENT_CATEGORY) && categoryDTO.getParentCategoryId() != null) {
            Category parentCategory = findByIdOrThrowEx(categoryDTO.getParentCategoryId());

            existedCategory.setParentCategory(parentCategory);
        }

        categoryMapper.updateCategory(incomingCategory, existedCategory);

        categoryRepository.save(existedCategory);

        return new SuccessResponse(categoryMapper.mapToCategoryDTO(existedCategory));
    }

    public BaseResponse softDeleteCategory(int id) {
        Category existedCategory = findByIdOrThrowEx(id);

        existedCategory.softDelete();

        categoryRepository.save(existedCategory);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteCategory(int id) {
        categoryRepository.deleteById(id);

        return new SuccessResponse(true);
    }

    protected Category findByIdOrThrowEx(int id) {
        Specification<Category> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return this.categoryRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại cần tìm với id: " + id));
    }
}
