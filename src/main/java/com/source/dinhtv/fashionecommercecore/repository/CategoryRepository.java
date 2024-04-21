package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.CATEGORY_TYPE;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.COLLECTION_TYPE;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {
    public static Specification<Category> isRegularCategory() {
        return (root, query, cb) -> cb.equal(root.get("type"), CATEGORY_TYPE);
    }

    public static Specification<Category> isCollection() {
        return (root, query, cb) -> cb.equal(root.get("type"), COLLECTION_TYPE);
    }
}
