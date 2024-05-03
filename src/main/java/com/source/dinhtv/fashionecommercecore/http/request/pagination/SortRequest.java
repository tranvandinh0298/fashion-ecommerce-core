package com.source.dinhtv.fashionecommercecore.http.request.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortRequest {
    private String key;
    private SortDirection direction;
}
