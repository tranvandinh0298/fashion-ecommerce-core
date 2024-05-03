package com.source.dinhtv.fashionecommercecore.http.request.pagination;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private String key;
    private Operator operator;
    private FieldType fieldType;
    private transient Object value;
    private transient Object valueTo;
    private transient List<Object> values;
}
