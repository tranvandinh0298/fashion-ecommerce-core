package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.review;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.review.ReviewDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.review.ReviewWithUserAndProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.UserMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.shipping.ShippingMapper;
import com.source.dinhtv.fashionecommercecore.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductMapper.class})
public interface ReviewMapper {
    /**
     * Only Review
     * */
    @Named("mapToReviewDTO")
    @Mapping(source = "id", target = "reviewId")
    ReviewDTO mapToReviewDTO(Review review);

    @Named("mapToReview")
    @Mapping(source = "reviewId", target = "id")
    Review mapToReview(ReviewDTO reviewDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromReviewDTO(ReviewDTO reviewDTO, @MappingTarget Review review);

    @Mapping(source = "id", target = "reviewId")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "product", target = "productDTO", qualifiedByName = "mapToProductDTO")
    ReviewWithUserAndProductDTO mapToReviewWithUserAndShippingDTO(Review review);
}
