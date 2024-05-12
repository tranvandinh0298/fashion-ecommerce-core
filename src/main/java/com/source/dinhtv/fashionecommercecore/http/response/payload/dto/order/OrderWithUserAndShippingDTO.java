package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.order;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.shipping.ShippingDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.user.UserDTO;
import com.source.dinhtv.fashionecommercecore.model.Shipping;
import com.source.dinhtv.fashionecommercecore.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderWithUserAndShippingDTO extends OrderDTO {
    public UserDTO userDTO;
    public ShippingDTO shippingDTO;
}
