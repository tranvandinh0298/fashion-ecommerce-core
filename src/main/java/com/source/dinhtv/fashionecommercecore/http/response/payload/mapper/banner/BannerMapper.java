package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.banner;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.banner.BannerDTO;
import com.source.dinhtv.fashionecommercecore.model.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BannerMapper {
    /**
     * Only Banner
     * */
    @Named("mapToBannerDTO")
    @Mapping(source = "id", target = "bannerId")
    BannerDTO mapToBannerDTO(Banner banner);

    @Named("mapToBanner")
    @Mapping(source = "bannerId", target = "id")
    Banner mapToBanner(BannerDTO bannerDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    void updateFromBannerDTO(BannerDTO bannerDTO, @MappingTarget Banner banner);
}
