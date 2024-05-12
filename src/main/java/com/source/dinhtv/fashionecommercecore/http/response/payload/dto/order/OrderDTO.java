package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.order;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.model.Shipping;
import com.source.dinhtv.fashionecommercecore.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class OrderDTO extends BaseDTO {
    public Integer orderId;
    public String orderNumber;
    public Integer userId;
    public Double subTotal;
    public Integer shippingId;
    public Double coupon;
    public Double totalAmount;
    public Integer quantity;
    public String paymentMethod;
    public String paymentStatus;
    public String status;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String country;
    public String postCode;
    public String address1;
    public String address2;
}
