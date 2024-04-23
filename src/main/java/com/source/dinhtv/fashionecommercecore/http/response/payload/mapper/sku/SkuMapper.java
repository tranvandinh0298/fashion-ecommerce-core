package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuWithOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuWithProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OptionMapper.class, ProductMapper.class})
public interface SkuMapper {
    /**
     * only Sku
     */
    @Mapping(source = "skuId", target = "id")
    Sku mapToSku(SkuDTO skuDTO);

    @Mapping(source = "id", target = "skuId")
    SkuDTO mapToSkuDTO(Sku Sku);

    /**
     * Sku & Option
     */
    @Mapping(source = "skuId", target = "id")
    @Mapping(source = "optionDTOs", target = "options")
    Sku mapToSku(SkuWithOptionsDTO skuDTO);

    @Mapping(source = "id", target = "skuId")
    @Mapping(source = "options", target = "optionDTOs")
    SkuWithOptionsDTO mapToSkuAndOptionsDTO(Sku Sku);

    /**
     * Sku & Product
     */
    @Mapping(source = "skuId", target = "id")
    @Mapping(source = "productDTO", target = "product")
    Sku mapToSku(SkuWithProductDTO skuDTO);

    @Mapping(source = "id", target = "skuId")
    @Mapping(source = "product", target = "productDTO")
    SkuWithProductDTO mapToSkuAndProductDTO(Sku Sku);
}
