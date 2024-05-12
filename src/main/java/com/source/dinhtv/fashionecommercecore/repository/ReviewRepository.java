package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.Review;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>, JpaSpecificationExecutor<Review> {
    public static Specification<Category> hasUserId(int userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }
    public static Specification<Category> hasProductId(int productId) {
        return (root, query, cb) -> cb.equal(root.get("product").get("id"), productId);
    }
}
