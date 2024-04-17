package com.source.dinhtv.fashionecommercecore.repository;

import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;

@Repository
public interface AttributeOptionRepository extends JpaRepository<AttributeOption, Integer>, JpaSpecificationExecutor<AttributeOption> {

    public static Specification<AttributeOption> withAttributeId(Integer attributeId) {
        return (root, query, cb) -> cb.equal(root.get("attribute").get("id"), attributeId);
    }
}
