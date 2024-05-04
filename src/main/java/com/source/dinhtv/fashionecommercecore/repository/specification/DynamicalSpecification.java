package com.source.dinhtv.fashionecommercecore.repository.specification;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.combineSpecs;

public class DynamicalSpecification<T> extends SearchSpecification<T>{
    private final List<Specification<T>> additionalSpecs;
    public DynamicalSpecification(SearchRequest request) {
        super(request);
        this.additionalSpecs = new ArrayList<>();
    }
    public DynamicalSpecification(SearchRequest request, List<Specification<T>> additionalSpecs) {
        super(request);
        this.additionalSpecs = additionalSpecs;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = super.toPredicate(root, query, cb);

        Specification<T> specs = combineSpecs(additionalSpecs);

        Specification<T> finalSpec = !Objects.isNull(predicate) ? specs.and((root1, query1, cb1) -> predicate) : specs;

        return finalSpec.toPredicate(root, query, cb);
    }
}
