package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.BannerController;
import com.source.dinhtv.fashionecommercecore.http.controller.CategoryController;
import com.source.dinhtv.fashionecommercecore.http.request.BannerFilter;
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

import static com.source.dinhtv.fashionecommercecore.repository.CategoryRepository.isRegularCategory;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
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
        new BannerFilter(request).convertFilterKey();

        SearchSpecification<Category> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Category> bannerPage = categoryRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<CategoryWithParentCategoryDTO>> BannerEntities = bannerPage.stream().map(
                category -> EntityModel.of(
                        categoryMapper.mapToCategoryWithParentCategoryDTO(category),
                        linkTo(methodOn(BannerController.class).getBannerById(category.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<CategoryWithParentCategoryDTO>> pagedModel = getPagedModel(BannerEntities, bannerPage.getNumber(), bannerPage.getSize(), bannerPage.getTotalElements(), bannerPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getCategoryById(int id) {
        Category existedCategory = findByIdOrThrowEx(id);

        CategoryDTO categoryDTO = categoryMapper.mapToCategoryDTO(existedCategory);

        Link allCategoriesLink = linkTo(methodOn(CategoryController.class).getAllCategories(new SearchRequest())).withRel("allCategories");

        EntityModel<CategoryDTO> categoryEntity = EntityModel.of(categoryDTO, allCategoriesLink);

        return new SuccessResponse(categoryEntity);
    }

    public BaseResponse createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.mapToCategory(categoryDTO);

        categoryRepository.save(category);

        return new SuccessResponse(categoryMapper.mapToCategoryDTO(category));
    }

    public BaseResponse updateCategory(int id, CategoryDTO categoryDTO) {
        Category existedCategory = findByIdOrThrowEx(id);

        categoryMapper.updateFromCategoryDTO(categoryDTO, existedCategory);

        categoryRepository.save(existedCategory);

        return new SuccessResponse(categoryMapper.mapToCategoryDTO(existedCategory));
    }

    public BaseResponse softDeleteCategory(int id) {
        Category existedCategory = findByIdOrThrowEx(id);

        existedCategory.softDelete();

        categoryRepository.save(existedCategory);

        return new SuccessResponse();
    }

    public BaseResponse deleteCategory(int id) {
        categoryRepository.deleteById(id);

        return new SuccessResponse();
    }

    private Category findByIdOrThrowEx(int id) {
        Specification<Category> spec = combineSpecs(List.of(
                hasId(id),
                isRegularCategory(),
                isNonDeletedRecord()
        ));
        return this.categoryRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại cần tìm với id: " + id));
    }
}
