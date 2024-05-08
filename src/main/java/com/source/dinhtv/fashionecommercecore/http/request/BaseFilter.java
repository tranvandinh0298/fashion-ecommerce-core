package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.FilterRequest;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.request.search.SortRequest;
import lombok.*;
import org.aspectj.weaver.ast.Var;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Getter
@Setter
public class BaseFilter<T> {
    private SearchRequest searchRequest;
    private List<Specification<T>> additionalSpecs;
    public BaseFilter(SearchRequest request) {
        this.searchRequest = request;
        additionalSpecs = new ArrayList<>();
    }

    public BaseFilter(SearchRequest searchRequest, List<Specification<T>> additionalSpecs) {
        this.searchRequest = searchRequest;
        this.additionalSpecs = additionalSpecs;
    }

    public List<Specification<T>> appendSpecs(Specification spec) {
        additionalSpecs.add(spec);

        return this.additionalSpecs;
    }

    public List<Specification<T>> appendSpecs(List<Specification<T>> specs) {
        if (additionalSpecs.size() == 0) {
            setAdditionalSpecs(specs);
        } else {
            setAdditionalSpecs(Stream.concat(specs.stream(), additionalSpecs.stream()).toList());
        }

        return additionalSpecs;
    }

    public SearchRequest convertFilterKey() {
        List<FilterRequest> filters = searchRequest.getFilters();
        Iterator<FilterRequest> filterIterator = filters.iterator();
        while (filterIterator.hasNext()) {
            FilterRequest filter = filterIterator.next();
            String entityFieldName = mapDtoFieldToEntityField(filter.getKey(), filter.getValue().toString());
            if (Objects.isNull(entityFieldName)) {
                filterIterator.remove();
            } else {
                filter.setKey(entityFieldName);
            }
        }

        List<SortRequest> sorts = searchRequest.getSorts();
        Iterator<SortRequest> sortIterator = sorts.iterator();
        while (sortIterator.hasNext()) {
            SortRequest sort = sortIterator.next();
            String entityFieldName = mapDtoFieldToEntityField(sort.getKey());
            if (Objects.isNull(entityFieldName)) {
                sortIterator.remove();
            } else {
                sort.setKey(entityFieldName);
            }
        }

        return searchRequest;
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        return null;
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        return null;
    }
}
