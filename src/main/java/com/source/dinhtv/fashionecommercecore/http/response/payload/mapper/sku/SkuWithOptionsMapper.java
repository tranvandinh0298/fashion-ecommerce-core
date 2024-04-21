package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.sku.SkuWithOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface SkuWithOptionsMapper extends SkuMapper{
    @Mapping(source = "optionDTOs", target = "options")
    Sku mapToSku(SkuWithOptionsDTO skuDTO);

    @Mapping(source = "options", target = "optionDTOs")
    SkuWithOptionsDTO mapToSkuDTO(Sku Sku);
}
