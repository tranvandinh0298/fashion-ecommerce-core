package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.coupon;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CouponDTO extends BaseDTO {
    public Integer couponId;
    public String code;
    public String type;
    public String value;
    public String status;
}
