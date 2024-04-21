package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Option;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeOptionRepository extends JpaRepository<Option, Integer>, JpaSpecificationExecutor<Option> {

    public static Specification<Option> withAttributeId(Integer attributeId) {
        return (root, query, cb) -> cb.equal(root.get("attribute").get("id"), attributeId);
    }

    public static Specification<Option> withNonDeletedAttribute() {
        return (root, query, cb) -> cb.isNull(root.get("attribute").get("deletedAt"));
    }
}
