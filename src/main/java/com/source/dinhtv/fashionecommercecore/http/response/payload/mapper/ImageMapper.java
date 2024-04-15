package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ImageDTO;
import com.source.dinhtv.fashionecommercecore.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDTO mapToImageDTO(Image image);

    Image mapToImage(ImageDTO imageDTO);
}
