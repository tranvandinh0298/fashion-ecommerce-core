package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IS_NOT_PARENT_CATEGORY;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IS_PARENT_CATEGORY;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    public static Specification<Category> hasUserId(int userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }
    public static Specification<Category> hasShippingId(int shippingId) {
        return (root, query, cb) -> cb.equal(root.get("shipping").get("id"), shippingId);
    }
}
