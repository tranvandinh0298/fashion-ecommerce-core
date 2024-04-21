package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.collection;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionWithImageAndProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface CollectionWithImageAndProductsMapper extends CollectionWithImageMapper{
    @Mapping(source = "products", target = "productDTOs")
    CollectionWithImageAndProductsDTO mapToCollectionDTO(Category collection);

    @Mapping(source = "productDTOs", target = "products")
    Category mapToCollection(CollectionWithImageAndProductsDTO collectionDTO);

    @Mapping(source = "collectionDTO.productDTOs", target = "collection.products")
    Category updateFromCollectionDTO(CollectionWithImageAndProductsDTO collectionDTO, @MappingTarget Category collection);
}
