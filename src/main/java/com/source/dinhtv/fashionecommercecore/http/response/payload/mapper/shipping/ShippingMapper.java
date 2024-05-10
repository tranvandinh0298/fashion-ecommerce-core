package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.shipping;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.shipping.ShippingDTO;
import com.source.dinhtv.fashionecommercecore.model.Shipping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ShippingMapper {
    /**
     * Only Shipping
     * */
    @Named("mapToShippingDTO")
    @Mapping(source = "id", target = "shippingId")
    ShippingDTO mapToShippingDTO(Shipping shipping);

    @Named("mapToShipping")
    @Mapping(source = "shippingId", target = "id")
    Shipping mapToShipping(ShippingDTO shippingDTO);

    @Mapping(target = "id", ignore = true)
    void updateFromShippingDTO(ShippingDTO shippingDTO, @MappingTarget Shipping shipping);
}
