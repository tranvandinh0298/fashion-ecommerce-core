package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.CategoryController;
import com.source.dinhtv.fashionecommercecore.http.controller.ImageController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    public BaseResponse getAllCategories(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Category> specs = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        Page<Category> categoriesPage = categoryRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (categoriesPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy thể loại/ bộ sưu tập nào");
        }

        List<EntityModel<CategoryDTO>> CategoryEntities = categoriesPage.stream().map(
                category -> EntityModel.of(
                        categoryMapper.mapToCategoryDTO(category),
                        linkTo(methodOn(CategoryController.class).getCategoryById(category.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<CategoryDTO>> pagedModel = getPagedModel(CategoryEntities,pageNum,pageSize, categoriesPage.getTotalElements(), categoriesPage.getTotalPages());

        return new SuccessResponse(pagedModel);

    }

    public BaseResponse getCategoryById(Integer id) {
        Specification<Category> spec = combineSpecs(List.of(
                hasId(id), isNonDeletedRecord()
        ));
        Category category = this.categoryRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại/ bộ sưu tập cần tìm với id: " + id));

        CategoryDTO categoryDTO = categoryMapper.mapToCategoryDTO(category);

        Link allCategoriesLink = linkTo(methodOn(ImageController.class).getAllImages(0,10)).withRel("allImages");

        EntityModel<CategoryDTO> categoryEntity = EntityModel.of(categoryDTO, allCategoriesLink);

        return new SuccessResponse(categoryEntity);
    }

    public BaseResponse createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.mapToCategory(categoryDTO);

        categoryRepository.save(category);

        return new SuccessResponse(categoryMapper.mapToCategoryDTO(category));
    }

    public BaseResponse updateCategory(Integer id, CategoryDTO categoryDTO) {
        Category existedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại/ bộ sưu tập cần tìm với id: " + id));

        categoryMapper.updateCategoryFromCategoryDTO(categoryDTO, existedCategory);

        categoryRepository.save(existedCategory);

        return new SuccessResponse(categoryMapper.mapToCategoryDTO(existedCategory));
    }

    public BaseResponse softDeleteCategory(Integer id) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thể loại/ bộ sưu tập cần tìm với id: " + id));

        existingCategory.softDelete();

        categoryRepository.save(existingCategory);

        return new SuccessResponse();
    }

    public BaseResponse deleteCategory(Integer id) {
        categoryRepository.deleteById(id);

        return new SuccessResponse();
    }
}
