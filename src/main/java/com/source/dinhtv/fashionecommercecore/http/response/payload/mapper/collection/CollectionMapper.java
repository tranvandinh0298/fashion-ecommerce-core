package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.collection;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, ImageMapper.class})
public interface CollectionMapper {
    @Mapping(source = "id", target = "collectionId")
    CollectionDTO mapToCollectionDTO(Category collection);

    @Mapping(source = "collectionId", target = "id")
    Category mapToCollection(CollectionDTO collectionDTO);

    @Mapping(target = "id", ignore = true)
    Category updateFromCollectionDTO(CollectionDTO collectionDTO, @MappingTarget Category collection);
}
