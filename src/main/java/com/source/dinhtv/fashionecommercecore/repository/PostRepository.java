package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.Post;
import com.source.dinhtv.fashionecommercecore.model.PostTag;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
    public static Specification<Category> hasUserId(int userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }
    public static Specification<Category> hasPostTagId(int tagId) {
        return (root, query, cb) -> cb.equal(root.get("postTag").get("id"), tagId);
    }
    public static Specification<Category> hasPostCategoryId(int postCategoryId) {
        return (root, query, cb) -> cb.equal(root.get("postCategory").get("id"), postCategoryId);
    }
}
