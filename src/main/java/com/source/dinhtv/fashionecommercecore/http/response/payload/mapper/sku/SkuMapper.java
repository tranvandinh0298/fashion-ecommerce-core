package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OptionMapper.class, ProductMapper.class})
public interface SkuMapper {
    @Mapping(source = "skuId", target = "id")
    Sku mapToSku(SkuDTO skuDTO);

    @Mapping(source = "id", target = "skuId")
    SkuDTO mapToSkuDTO(Sku Sku);
}
