package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;

import static com.source.dinhtv.fashionecommercecore.repository.OrderRepository.hasShippingId;
import static com.source.dinhtv.fashionecommercecore.repository.OrderRepository.hasUserId;
import static com.source.dinhtv.fashionecommercecore.repository.ReviewRepository.hasProductId;

@Getter
@Setter
public class ReviewFilter extends BaseFilter{
    public ReviewFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "reviewId":
                return "id";
            case "userId":
                appendSpecs(hasUserId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "productId":
                appendSpecs(hasProductId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "rate":
                return "rate";
            case "review":
                return "review";
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
