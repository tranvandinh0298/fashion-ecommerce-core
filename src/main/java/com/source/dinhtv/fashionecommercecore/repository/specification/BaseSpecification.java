package com.source.dinhtv.fashionecommercecore.repository.specification;

import com.source.dinhtv.fashionecommercecore.utils.CustomConstants;
import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T> {
    public static Specification<T> withNonDeletedRecord() {
        return (root, query, cb) -> cb.isNull(root.get("deleted_at"));
    }

    public Specification<T> withDeletedRecord() {
        return (root, query, cb) -> cb.isNotNull(root.get("deleted_at"));
    }

    public Specification<T> withActiveRecord() {
        return (root, query, cb) -> cb.equal(root.get("status"), CustomConstants.RECORD_STATUS_ACTIVE);
    }

    public Specification<T> withInactiveRecord() {
        return (root, query, cb) -> cb.equal(root.get("status"), CustomConstants.RECORD_STATUS_INACTIVE);
    }
}
