package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuWithProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface SkuWithProductMapper extends SkuMapper{
    @Mapping(source = "productDTO", target = "product")
    Sku mapToSku(SkuWithProductDTO skuDTO);

    @Mapping(source = "product", target = "productDTO")
    SkuWithProductDTO mapToSkuDTO(Sku Sku);
}
