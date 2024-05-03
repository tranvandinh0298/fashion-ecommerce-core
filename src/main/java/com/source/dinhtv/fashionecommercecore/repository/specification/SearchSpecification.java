package com.source.dinhtv.fashionecommercecore.repository.specification;

import com.source.dinhtv.fashionecommercecore.http.request.pagination.FilterRequest;
import com.source.dinhtv.fashionecommercecore.http.request.pagination.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.request.pagination.SortRequest;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
    private final transient SearchRequest request;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.equal(cb.literal(Boolean.TRUE), Boolean.TRUE);

        List<FilterRequest> filters = this.request.getFilters();
        for (FilterRequest filter : filters) {
            predicate = filter.getOperator().build(root, cb, filter, predicate);
        }

        List<Order> orders = new ArrayList<>();
        for (SortRequest sort : this.request.getSorts()) {
            orders.add(sort.getDirection().build(root, cb, sort));
        }

        query.orderBy(orders);
        return predicate;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageRequest.of(Objects.requireNonNullElse(page, 0), Objects.requireNonNullElse(size, 100));
    }
}
