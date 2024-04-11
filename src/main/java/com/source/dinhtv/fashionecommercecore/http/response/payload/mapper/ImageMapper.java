package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ImageDTO;
import com.source.dinhtv.fashionecommercecore.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageMapper MAPPER = Mappers.getMapper(ImageMapper.class);

//    @Mapping(source = "id", target = "imageId")
//    @Mapping(source = "caption", target = "caption")
//    @Mapping(source = "address", target = "address")
    ImageDTO mapToImageDTO(Image image);

//    @Mapping(source = "imageId", target = "id")
//    @Mapping(source = "caption", target = "caption")
//    @Mapping(source = "address", target = "address")
    Image mapToImage(ImageDTO imageDTO);
}
