package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.brand;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.brand.BrandDTO;
import com.source.dinhtv.fashionecommercecore.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    /**
     * Only Brand
     * */
    @Mapping(source = "id", target = "brandId")
    BrandDTO mapToBrandDTO(Brand brand);

    @Mapping(source = "brandId", target = "id")
    Brand mapToBrand(BrandDTO brandDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    void updateFromBrandDTO(BrandDTO brandDTO, @MappingTarget Brand brand);
}
