package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    public static Specification<Category> hasCategoryId(int categoryId) {
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }
    public static Specification<Category> hasChildCategoryId(int childCategoryId) {
        return (root, query, cb) -> cb.equal(root.get("childCategory").get("id"), childCategoryId);
    }
    public static Specification<Category> hasBrandId(int brandId) {
        return (root, query, cb) -> cb.equal(root.get("brand").get("id"), brandId);
    }
}
