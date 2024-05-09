package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.CouponController;
import com.source.dinhtv.fashionecommercecore.http.request.CouponFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.coupon.CouponDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.coupon.CouponMapper;
import com.source.dinhtv.fashionecommercecore.model.Coupon;
import com.source.dinhtv.fashionecommercecore.repository.CouponRepository;
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
public class CouponService {
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponMapper couponMapper;

    public BaseResponse getAllCoupons(SearchRequest request) {
        new CouponFilter(request).convertFilterKey();

        SearchSpecification<Coupon> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Coupon> couponPage = couponRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<CouponDTO>> CouponEntities = couponPage.stream().map(
                coupon -> EntityModel.of(
                        couponMapper.mapToCouponDTO(coupon),
                        linkTo(methodOn(CouponController.class).getCouponById(coupon.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<CouponDTO>> pagedModel = getPagedModel(CouponEntities, couponPage.getNumber(), couponPage.getSize(), couponPage.getTotalElements(), couponPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getCouponById(int id) {
        Coupon existedCoupon = findByIdOrThrowEx(id);

        CouponDTO couponDTO = couponMapper.mapToCouponDTO(existedCoupon);

        Link allCouponsLink = linkTo(methodOn(CouponController.class).getAllCoupons(new SearchRequest())).withRel("allCoupons");

        EntityModel<CouponDTO> couponEntity = EntityModel.of(couponDTO, allCouponsLink);

        return new SuccessResponse(couponEntity);
    }

    public BaseResponse createCoupon(CouponDTO couponDTO) {
        Coupon coupon = couponMapper.mapToCoupon(couponDTO);

        couponRepository.save(coupon);

        return new SuccessResponse(couponMapper.mapToCouponDTO(coupon));
    }

    public BaseResponse updateCoupon(int id, CouponDTO couponDTO) {
        Coupon existedCoupon = findByIdOrThrowEx(id);

        couponMapper.updateFromCouponDTO(couponDTO, existedCoupon);

        couponRepository.save(existedCoupon);

        return new SuccessResponse(couponMapper.mapToCouponDTO(existedCoupon));
    }

    public BaseResponse softDeleteCoupon(int id) {
        Coupon existedCoupon = findByIdOrThrowEx(id);

        existedCoupon.softDelete();

        couponRepository.save(existedCoupon);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteCoupon(int id) {
        couponRepository.deleteById(id);

        return new SuccessResponse();
    }

    private Coupon findByIdOrThrowEx(int id) {
        Specification<Coupon> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return couponRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy coupon cần tìm với id: " + id));
    }
}
