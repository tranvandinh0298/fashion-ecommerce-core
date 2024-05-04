package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.banner;

import com.source.dinhtv.fashionecommercecore.http.request.search.FilterRequest;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.request.search.SortRequest;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class BannerMapperService {
    public static SearchRequest convertFilterKey(SearchRequest searchRequest) {
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

    public static String mapDtoFieldToEntityField(String dtoFieldName) {
        switch (dtoFieldName) {
            case "bannerId":
                return "id";
            case "title":
                return "title";
            case "slug":
                return "slug";
            default:
                return null;
        }
    }
}
