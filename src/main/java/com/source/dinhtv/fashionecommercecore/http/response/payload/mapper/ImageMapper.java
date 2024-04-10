package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ImageDTO;
import com.source.dinhtv.fashionecommercecore.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper mapper = Mappers.getMapper(ImageMapper.class);

    ImageDTO mapToImageDTO(Image image);

    Image mapToImage(ImageDTO imageDTO);
}
