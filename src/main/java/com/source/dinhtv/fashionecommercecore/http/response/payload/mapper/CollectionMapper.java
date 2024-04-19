package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ImageMapper.class})
public interface CollectionMapper {
    @Mapping(source = "id", target = "collectionId")
    @Mapping(source = "image", target = "imageDTO")
    CollectionDTO mapToCollectionDTO(Category collection);

    @Mapping(source = "collectionId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    Category mapToCollection(CollectionDTO collectionDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "collectionDTO.imageDTO", target = "collection.image")
    Category updateFromCollectionDTO(CollectionDTO collectionDTO, @MappingTarget Category collection);
}
