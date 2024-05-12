package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;

import static com.source.dinhtv.fashionecommercecore.repository.OrderRepository.hasShippingId;
import static com.source.dinhtv.fashionecommercecore.repository.OrderRepository.hasUserId;

@Getter
@Setter
public class OrderFilter<Category> extends BaseFilter<Category> {
    public OrderFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "orderId":
                return "id";
            case "orderNumber":
                return "orderNumber";
            case "userId":
                appendSpecs(hasUserId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "subTotal":
                return "subTotal";
            case "shippingId":
                appendSpecs(hasShippingId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "coupon":
                return "coupon";
            case "totalAmount":
                return "totalAmount";
            case "quantity":
                return "quantity";
            case "paymentMethod":
                return "paymentMethod";
            case "paymentStatus":
                return "paymentStatus";
            case "status":
                return "status";
            case "firstName":
                return "firstName";
            case "lastName":
                return "lastName";
            case "email":
                return "email";
            case "phone":
                return "phone";
            case "country":
                return "country";
            case "postCode":
                return "postCode";
            case "address1":
                return "address1";
            case "address2":
                return "address2";
            default:
                return null;
        }
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        if (dtoFieldName.equals("userId") || dtoFieldName.equals("shippingId")) {
            return null;
        }

        return mapDtoFieldToEntityField(dtoFieldName, null);
    }
}
