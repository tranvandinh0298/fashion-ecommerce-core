package com.source.dinhtv.fashionecommercecore.repository.specification;

import com.source.dinhtv.fashionecommercecore.utils.CustomConstants;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public abstract class UserSpecification {
    public static <User> Specification<User> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get("email"), email);
    }
}
