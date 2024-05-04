package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.*;

@Getter
@Setter
public class BannerFilter extends BaseFilter{
    public BannerFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
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
