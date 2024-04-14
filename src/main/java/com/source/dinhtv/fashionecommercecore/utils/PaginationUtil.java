package com.source.dinhtv.fashionecommercecore.utils;

import com.source.dinhtv.fashionecommercecore.exception.RestAPIException;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

public class PaginationUtil {

    public static <T> PagedModel<EntityModel<T>> getPagedModel(List<EntityModel<T>> entities, int pageNum, int pageSize, long totalElements, int totalPages) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        PagedModel.PageMetadata metadata = new PagedModel.PageMetadata(pageSize, pageNum, totalElements, totalPages);

        PagedModel<EntityModel<T>> pagedModel = PagedModel.of(entities, metadata);

        addPaginationLinks(pagedModel, baseUrl, pageNum, totalPages);

        return pagedModel;
    }

    public static void verifyPageNumAndSize(int pageNum, int pageSize) {
        if (pageNum < 0) {
            throw new RestAPIException("Số trang không thể nhỏ hơn 0");
        }

        if (pageSize < 0) {
            throw new RestAPIException("Số bản ghi của mỗi trang không thể nhỏ hơn 0 ");
        }
    }

    private static <T> void addPaginationLinks(PagedModel<EntityModel<T>> pagedModel, String baseUrl, int pageNum, int totalPages) {
        Link selfLink = Link.of(baseUrl).withSelfRel();
        pagedModel.add(selfLink);

        if (pageNum > 0) {
            Link firstLink = Link.of(getPageUrl(baseUrl, 0)).withRel("first");
            Link prevLink = Link.of(getPageUrl(baseUrl, pageNum - 1)).withRel("prev");
            pagedModel.add(firstLink, prevLink);
        }

        if (pageNum < totalPages - 1) {
            Link nextLink = Link.of(getPageUrl(baseUrl, pageNum + 1)).withRel("next");
            Link lastLink = Link.of(getPageUrl(baseUrl, totalPages - 1)).withRel("last");
            pagedModel.add(nextLink, lastLink);
        }
    }

    private static String getPageUrl(String baseUrl, int pageNum) {
        return baseUrl + "?page=" + pageNum;
    }

}
