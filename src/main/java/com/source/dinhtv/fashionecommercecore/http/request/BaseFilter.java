package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.FilterRequest;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.request.search.SortRequest;
import lombok.*;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class BaseFilter {
    private SearchRequest searchRequest;
    public BaseFilter(SearchRequest request) {
        this.searchRequest = request;
    }
    public SearchRequest convertFilterKey() {
        List<FilterRequest> filters = searchRequest.getFilters();
        Iterator<FilterRequest> filterIterator = filters.iterator();
        while (filterIterator.hasNext()) {
            FilterRequest filter = filterIterator.next();
            String entityFieldName = mapDtoFieldToEntityField(filter.getKey());
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
}