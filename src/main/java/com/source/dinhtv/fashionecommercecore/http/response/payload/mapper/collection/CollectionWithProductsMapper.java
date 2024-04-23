package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.collection;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionWithProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, ImageMapper.class})
public interface CollectionWithProductsMapper extends CollectionMapper{
    @Mapping(source = "products", target = "productDTOs")
    CollectionWithProductsDTO mapToCategoryDTO(Category collection);

    @Mapping(source = "productDTOs", target = "products")
    Category mapToCollection(CollectionWithProductsDTO collectionDTO);

    @Mapping(source = "collectionDTO.productDTOs", target = "collection.products")
    Category updateFromCollectionDTO(CollectionWithProductsDTO collectionDTO, @MappingTarget Category collection);
}
