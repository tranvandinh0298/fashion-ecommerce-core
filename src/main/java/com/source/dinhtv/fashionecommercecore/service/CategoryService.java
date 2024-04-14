package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.User;
import com.source.dinhtv.fashionecommercecore.repository.CategoryRepository;
import com.source.dinhtv.fashionecommercecore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.combineSpecs;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.isNonDeletedRecord;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public BaseResponse getAllCategories() {
        Specification<Category> specs = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        List<Category> categories = categoryRepository.findAll(specs);

        return new SuccessResponse();

    }

    public BaseResponse getCategoryById(Integer id) {

        return new SuccessResponse();
    }

    public BaseResponse createCategory(User user) {
        return new SuccessResponse();
    }

    public BaseResponse updateCategory(Integer id, UserDTO userDTO) {
        return new SuccessResponse();
    }

    public BaseResponse deleteCategory(Integer id) {
        return new SuccessResponse();
    }
}
