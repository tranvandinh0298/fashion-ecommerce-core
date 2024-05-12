package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.order;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.order.OrderDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.order.OrderWithUserAndShippingDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.UserMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.shipping.ShippingMapper;
import com.source.dinhtv.fashionecommercecore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ShippingMapper.class})
public interface OrderMapper {
    /**
     * Only Order
     * */
    @Named("mapToOrderDTO")
    @Mapping(source = "id", target = "orderId")
    OrderDTO mapToOrderDTO(Order order);

    @Named("mapToOrder")
    @Mapping(source = "orderId", target = "id")
    Order mapToOrder(OrderDTO orderDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromOrderDTO(OrderDTO orderDTO, @MappingTarget Order order);

    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "shipping", target = "shippingDTO")
    OrderWithUserAndShippingDTO mapToOrderWithUserAndShippingDTO(Order order);
}
