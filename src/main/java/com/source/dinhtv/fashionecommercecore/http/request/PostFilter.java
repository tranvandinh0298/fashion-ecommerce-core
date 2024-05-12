package com.source.dinhtv.fashionecommercecore.http.request;

import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import lombok.Getter;
import lombok.Setter;

import static com.source.dinhtv.fashionecommercecore.repository.CategoryRepository.hasParentCategoryId;
import static com.source.dinhtv.fashionecommercecore.repository.PostRepository.*;

@Getter
@Setter
public class PostFilter extends BaseFilter{
    public PostFilter(SearchRequest request) {
        super(request);
    }

    public String mapDtoFieldToEntityField(String dtoFieldName, String dtoFieldValue) {
        switch (dtoFieldName) {
            case "postId":
                return "id";
            case "title":
                return "title";
            case "slug":
                return "slug";
            case "summary":
                return "summary";
            case "description":
                return "description";
            case "quote":
                return "quote";
            case "tags":
                return "tags";
            case "postTagId":
                appendSpecs(hasPostTagId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "postCategoryId":
                appendSpecs(hasPostCategoryId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "userId":
                appendSpecs(hasUserId(Integer.parseInt(dtoFieldValue)));
                return null;
            case "status":
                return "status";
            default:
                return null;
        }
    }

    public String mapDtoFieldToEntityField(String dtoFieldName) {
        if (dtoFieldName.equals("postTagId") || dtoFieldName.equals("postCategoryId") || dtoFieldName.equals("userId")) {
            return null;
        }

        return mapDtoFieldToEntityField(dtoFieldName, null);
    }
}
