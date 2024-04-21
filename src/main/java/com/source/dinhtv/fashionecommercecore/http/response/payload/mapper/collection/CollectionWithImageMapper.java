package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.collection;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionWithImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface CollectionWithImageMapper extends CollectionMapper{
    @Mapping(source = "image", target = "imageDTO")
    CollectionWithImageDTO mapToCollectionDTO(Category collection);

    @Mapping(source = "imageDTO", target = "image")
    Category mapToCollection(CollectionWithImageDTO collectionDTO);

    @Mapping(source = "collectionDTO.imageDTO", target = "collection.image")
    Category updateFromCollectionDTO(CollectionWithImageDTO collectionDTO, @MappingTarget Category collection);
}
