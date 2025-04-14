package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.*;

import static com.source.dinhtv.fashionecommercecore.repository.CategoryRepository.hasParentCategoryId;

@Getter
@Setter
public class BannerFilter extends BaseFilter{
    public BannerFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "bannerId":
                return "id";
            case "title":
                return "title";
            case "slug":
                return "slug";
            case "status":
                return "status";
            case "createdAt":
                return "createdAt";
            case "updatedAt":
                return "updatedAt";
            default:
                return null;
        }
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        return mapDtoFieldToEntityField(dtoFieldName, null);
    }
}
