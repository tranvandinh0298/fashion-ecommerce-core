package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandFilter extends BaseFilter{
    public BrandFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "brandId":
                return "id";
            case "title":
                return "title";
            case "slug":
                return "slug";
            case "status":
                return "status";
            default:
                return null;
        }
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        return mapDtoFieldToEntityField(dtoFieldName, null);
    }
}
