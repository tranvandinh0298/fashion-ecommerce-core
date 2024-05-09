package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;

import static com.source.dinhtv.fashionecommercecore.repository.ProductRepository.*;

@Getter
@Setter
public class ProductFilter<Category> extends BaseFilter<Category> {
    public ProductFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "productId":
                return "id";
            case "title":
                return "title";
            case "slug":
                return "slug";
            case "summary":
                return "summary";
            case "description":
                return "description";
            case "condition":
                return "condition";
            case "status":
                return "status";
            case "price":
                return "price";
            case "isFeatured" :
                return "isFeatured";
            case "categoryId":
                appendSpecs(hasCategoryId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "childCategoryId":
                appendSpecs(hasChildCategoryId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "brandId":
                appendSpecs(hasBrandId(Integer.parseInt(dtoFieldValue)));
                return null;
            default:
                return null;
        }
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        if (dtoFieldName.equals("parentCategoryId") || dtoFieldName.equals("childCategoryId") || dtoFieldName.equals("brandId")) {
            return null;
        }

        return mapDtoFieldToEntityField(dtoFieldName, null);
    }
}
