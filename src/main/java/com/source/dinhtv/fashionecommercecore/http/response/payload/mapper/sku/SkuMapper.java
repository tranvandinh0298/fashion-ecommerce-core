package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuWithOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuWithProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {OptionMapper.class, ProductMapper.class})
public interface SkuMapper {
    /**
     * only Sku
     */
    @Named("mapToSku")
    @Mapping(source = "skuId", target = "id")
    Sku mapToSku(SkuDTO skuDTO);

    @Named("mapToSkuDTO")
    @Mapping(source = "id", target = "skuId")
    SkuDTO mapToSkuDTO(Sku Sku);

    /**
     * Sku & Option
     */
//    @Mapping(source = "skuId", target = "id")
//    @Mapping(source = "optionDTOs", target = "options", qualifiedByName = "mapToOption")
//    Sku mapToSku(SkuWithOptionsDTO skuDTO);
//
//    @Mapping(source = "id", target = "skuId")
//    @Mapping(source = "options", target = "optionDTOs", qualifiedByName = "mapToOptionDTO")
//    SkuWithOptionsDTO mapToSkuAndOptionsDTO(Sku Sku);

    /**
     * Sku & Product
     */
//    @Mapping(source = "skuId", target = "id")
//    @Mapping(source = "productDTO", target = "product", qualifiedByName = "mapToProduct")
//    Sku mapToSku(SkuWithProductDTO skuDTO);
//
//    @Mapping(source = "id", target = "skuId")
//    @Mapping(source = "product", target = "productDTO", qualifiedByName = "mapToProductDTO")
//    SkuWithProductDTO mapToSkuAndProductDTO(Sku Sku);
}
