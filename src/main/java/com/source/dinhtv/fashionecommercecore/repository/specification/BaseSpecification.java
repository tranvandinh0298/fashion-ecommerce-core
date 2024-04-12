package com.source.dinhtv.fashionecommercecore.repository.specification;

import com.source.dinhtv.fashionecommercecore.utils.CustomConstants;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class BaseSpecification<T> {
    public static <T> Specification<T> hasId(Integer id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static <T> Specification<T> isNonDeletedRecord() {
        return (root, query, cb) -> cb.isNull(root.get("deletedAt"));
    }

    public static <T> Specification<T> isDeletedRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("deletedAt"));
    }

    public static <T> Specification<T> withActiveRecord() {
        return (root, query, cb) -> cb.equal(root.get("status"), CustomConstants.RECORD_STATUS_ACTIVE);
    }

    public static <T> Specification<T> withInactiveRecord() {
        return (root, query, cb) -> cb.equal(root.get("status"), CustomConstants.RECORD_STATUS_INACTIVE);
    }

    // Method to combine multiple specifications with 'and'
    public static <T> Specification<T> combineSpecs(List<Specification<T>> specs) {
        Specification<T> combinedSpec = Specification.where(null);
        for (Specification<T> spec : specs) {
            combinedSpec = combinedSpec.and(spec);
        }
        return combinedSpec;
    }
}
