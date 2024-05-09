package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.coupon;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.coupon.CouponDTO;
import com.source.dinhtv.fashionecommercecore.model.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    /**
     * Only Coupon
     * */
    @Mapping(source = "id", target = "couponId")
    CouponDTO mapToCouponDTO(Coupon coupon);

    @Mapping(source = "couponId", target = "id")
    Coupon mapToCoupon(CouponDTO couponDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromCouponDTO(CouponDTO couponDTO, @MappingTarget Coupon coupon);
}
