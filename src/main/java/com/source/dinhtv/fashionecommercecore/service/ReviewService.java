package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.ReviewController;
import com.source.dinhtv.fashionecommercecore.http.request.ReviewFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.review.ReviewDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.review.ReviewWithUserAndProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.review.ReviewMapper;
import com.source.dinhtv.fashionecommercecore.model.Review;
import com.source.dinhtv.fashionecommercecore.repository.ReviewRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.DynamicalSpecification;
import com.source.dinhtv.fashionecommercecore.repository.specification.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;

    public BaseResponse getAllReviews(SearchRequest request) {
        new ReviewFilter(request).convertFilterKey();

        SearchSpecification<Review> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Review> reviewPage = reviewRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<ReviewWithUserAndProductDTO>> ReviewEntities = reviewPage.stream().map(
                review -> EntityModel.of(
                        reviewMapper.mapToReviewWithUserAndShippingDTO(review),
                        linkTo(methodOn(ReviewController.class).getReviewById(review.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<ReviewWithUserAndProductDTO>> pagedModel = getPagedModel(ReviewEntities, reviewPage.getNumber(), reviewPage.getSize(), reviewPage.getTotalElements(), reviewPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllReviewsWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter reviewFilter = new ReviewFilter(request);
        reviewFilter.convertFilterKey();
        reviewFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Review> specs = new DynamicalSpecification<>(request, reviewFilter.getAdditionalSpecs());

        // reviews
        List<Review> reviews = reviewRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<ReviewWithUserAndProductDTO>> ReviewEntities = reviews.stream().map(
                review -> EntityModel.of(
                        reviewMapper.mapToReviewWithUserAndShippingDTO(review),
                        linkTo(methodOn(ReviewController.class).getReviewById(review.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(ReviewEntities);
    }

    public BaseResponse getReviewById(int id) {
        Review existedReview = findByIdOrThrowEx(id);

        ReviewDTO reviewDTO = reviewMapper.mapToReviewDTO(existedReview);

        Link allReviewsLink = linkTo(methodOn(ReviewController.class).getAllReviews(new SearchRequest())).withRel("allReviews");

        EntityModel<ReviewDTO> reviewEntity = EntityModel.of(reviewDTO, allReviewsLink);

        return new SuccessResponse(reviewEntity);
    }

    public BaseResponse createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.mapToReview(reviewDTO);

        reviewRepository.save(review);

        return new SuccessResponse(reviewMapper.mapToReviewDTO(review));
    }

    public BaseResponse updateReview(int id, ReviewDTO reviewDTO) {
        Review existedReview = findByIdOrThrowEx(id);

        reviewMapper.updateFromReviewDTO(reviewDTO, existedReview);

        reviewRepository.save(existedReview);

        return new SuccessResponse(reviewMapper.mapToReviewDTO(existedReview));
    }

    public BaseResponse softDeleteReview(int id) {
        Review existedReview = findByIdOrThrowEx(id);

        existedReview.softDelete();

        reviewRepository.save(existedReview);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteReview(int id) {
        reviewRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected Review findByIdOrThrowEx(int id) {
        Specification<Review> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return reviewRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy review cần tìm với id: " + id));
    }
}
