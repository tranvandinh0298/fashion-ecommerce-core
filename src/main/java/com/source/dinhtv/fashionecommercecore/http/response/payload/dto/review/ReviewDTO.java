package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.review;

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
public class ReviewDTO extends BaseDTO {
    public Integer reviewId;
    public Integer userId;
    public Integer productId;
    public Integer rate;
    public Integer review;
    public String status;
}