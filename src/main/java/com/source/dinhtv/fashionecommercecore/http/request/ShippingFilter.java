package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingFilter extends BaseFilter{
    public ShippingFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "shippingId":
                return "id";
            case "type":
                return "type";
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
