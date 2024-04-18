package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.SkuDTO;
import com.source.dinhtv.fashionecommercecore.model.Product;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AttributeOptionMapper.class})
public interface SkuMapper {
    @Mapping(source = "optionDTOs", target = "options")
    Sku mapToSku(SkuDTO skuDTO);

    @Mapping(source = "options", target = "optionDTOs")
    SkuDTO mapToSkuDTO(Sku Sku);
}
