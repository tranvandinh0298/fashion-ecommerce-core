package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.collection;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionWithImageAndProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionWithImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection.CollectionWithProductsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ProductMapper.class, ImageMapper.class})
public interface CollectionMapper {
    /**
     * Only Collection
     * */
    @Mapping(source = "id", target = "collectionId")
    CollectionDTO mapToCollectionDTO(Category collection);

    @Mapping(source = "collectionId", target = "id")
    Category mapToCollection(CollectionDTO collectionDTO);

    @Mapping(target = "id", ignore = true)
    Category updateFromCollectionDTO(CollectionDTO collectionDTO, @MappingTarget Category collection);

//    /**
//     * Collection & Image
//     * */
//    @Mapping(source = "id", target = "collectionId")
//    @Mapping(source = "image", target = "imageDTO", qualifiedByName = "mapToImageDTO")
//    CollectionWithImageDTO mapToCollectionAndImageDTO(Category collection);
//
//    @Mapping(source = "collectionId", target = "id")
//    @Mapping(source = "imageDTO", target = "image", qualifiedByName = "mapToImage")
//    Category mapToCollection(CollectionWithImageDTO collectionDTO);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "collectionDTO.imageDTO", target = "collection.image", qualifiedByName = "mapToImage")
//    Category updateFromCollectionDTO(CollectionWithImageDTO collectionDTO, @MappingTarget Category collection);

//    /**
//     * Collection & Products
//     * */
//    @Mapping(source = "id", target = "collectionId")
//    @Mapping(source = "products", target = "productDTOs", qualifiedByName = "mapToProductDTO")
//    CollectionWithProductsDTO mapToCollectionAndProductsDTO(Category collection);
//
//    @Mapping(source = "collectionId", target = "id")
//    @Mapping(source = "productDTOs", target = "products", qualifiedByName = "mapToProduct")
//    Category mapToCollection(CollectionWithProductsDTO collectionDTO);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "collectionDTO.productDTOs", target = "collection.products", qualifiedByName = "mapToProduct")
//    Category updateFromCollectionDTO(CollectionWithProductsDTO collectionDTO, @MappingTarget Category collection);

//    /**
//     * Collection & Image & Products
//     * */
//    @Mapping(source = "id", target = "collectionId")
//    @Mapping(source = "image", target = "imageDTO", qualifiedByName = "mapToImageDTO")
//    @Mapping(source = "products", target = "productDTOs", qualifiedByName = "mapToProductDTO")
//    CollectionWithImageAndProductsDTO mapToCollectionAndImageAndProductsDTO(Category collection);
//
//    @Mapping(source = "collectionId", target = "id")
//    @Mapping(source = "imageDTO", target = "image", qualifiedByName = "mapToImage")
//    @Mapping(source = "productDTOs", target = "products", qualifiedByName = "mapToProduct")
//    Category mapToCollection(CollectionWithImageAndProductsDTO collectionDTO);
//
//    @Mapping(target = "id", ignore = true)
//    @Mapping(source = "collectionDTO.imageDTO", target = "collection.image", qualifiedByName = "mapToImage")
//    @Mapping(source = "collectionDTO.productDTOs", target = "collection.products", qualifiedByName = "mapToProduct")
//    Category updateFromCollectionDTO(CollectionWithImageAndProductsDTO collectionDTO, @MappingTarget Category collection);
}
