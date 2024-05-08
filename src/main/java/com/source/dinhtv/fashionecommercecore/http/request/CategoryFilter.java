package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.source.dinhtv.fashionecommercecore.repository.CategoryRepository.hasParentCategoryId;

@Getter
@Setter
public class CategoryFilter<Category> extends BaseFilter<Category> {
    public CategoryFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "categoryId":
                return "id";
            case "title":
                return "title";
            case "slug":
                return "slug";
            case "summary":
                return "summary";
            case "status":
                return "status";
            case "isParent":
                return "isParent";
            case "parentCategoryId":
                appendSpecs(hasParentCategoryId(Integer.parseInt(dtoFieldValue)));
                return null;
            default:
                return null;
        }
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        if (dtoFieldName.equals("parentCategoryId")) {
            return null;
        }

        return mapDtoFieldToEntityField(dtoFieldName, null);
    }
}
