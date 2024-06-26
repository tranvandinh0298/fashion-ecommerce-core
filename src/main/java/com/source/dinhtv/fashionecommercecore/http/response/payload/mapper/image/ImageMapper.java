package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
import com.source.dinhtv.fashionecommercecore.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Named("mapToImageDTO")
    @Mapping(source = "id", target = "imageId")
    ImageDTO mapToImageDTO(Image image);

    @Named("mapToImage")
    @Mapping(source = "imageId", target = "id")
    Image mapToImage(ImageDTO imageDTO);
}
