package com.source.dinhtv.fashionecommercecore.http.request.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.DEFAULT_PAGE_NUMBER;
import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
    private List<FilterRequest> filters;

    private List<SortRequest> sorts;

    private Integer page = Integer.valueOf(DEFAULT_PAGE_NUMBER);

    private Integer size = Integer.valueOf(DEFAULT_PAGE_SIZE);

    public List<FilterRequest> getFilters() {
        if (Objects.isNull(this.filters)) return new ArrayList<>();
        return this.filters;
    }

    public List<SortRequest> getSorts() {
        if (Objects.isNull(this.sorts)) return new ArrayList<>();
        return this.sorts;
    }
}
