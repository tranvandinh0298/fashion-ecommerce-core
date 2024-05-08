package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
    public static Specification<Category> isParent() {
        return (root, query, cb) -> cb.equal(root.get("is_parent"), IS_PARENT_CATEGORY);
    }

    public static Specification<Category> isNotParent() {
        return (root, query, cb) -> cb.equal(root.get("is_parent"), IS_NOT_PARENT_CATEGORY);
    }

    public static Specification<Category> hasParentCategoryId(int parentCategoryId) {
        return (root, query, cb) -> cb.equal(root.get("parentCategory").get("id"), parentCategoryId);
    }
}
