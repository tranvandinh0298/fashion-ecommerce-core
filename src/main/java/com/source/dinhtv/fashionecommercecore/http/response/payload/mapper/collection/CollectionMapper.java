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

    /**
     * Collection & Image
     * */
    @Mapping(source = "id", target = "collectionId")
    @Mapping(source = "image", target = "imageDTO")
    CollectionWithImageDTO mapToCollectionAndImageDTO(Category collection);

    @Mapping(source = "collectionId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    Category mapToCollection(CollectionWithImageDTO collectionDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "collectionDTO.imageDTO", target = "collection.image")
    Category updateFromCollectionDTO(CollectionWithImageDTO collectionDTO, @MappingTarget Category collection);

    /**
     * Collection & Products
     * */
    @Mapping(source = "id", target = "collectionId")
    @Mapping(source = "products", target = "productDTOs")
    CollectionWithProductsDTO mapToCollectionAndProductsDTO(Category collection);

    @Mapping(source = "collectionId", target = "id")
    @Mapping(source = "productDTOs", target = "products")
    Category mapToCollection(CollectionWithProductsDTO collectionDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "collectionDTO.productDTOs", target = "collection.products")
    Category updateFromCollectionDTO(CollectionWithProductsDTO collectionDTO, @MappingTarget Category collection);

    /**
     * Collection & Image & Products
     * */
    @Mapping(source = "id", target = "collectionId")
    @Mapping(source = "image", target = "imageDTO")
    @Mapping(source = "products", target = "productDTOs")
    CollectionWithImageAndProductsDTO mapToCollectionAndImageAndProductsDTO(Category collection);

    @Mapping(source = "collectionId", target = "id")
    @Mapping(source = "imageDTO", target = "image")
    @Mapping(source = "productDTOs", target = "products")
    Category mapToCollection(CollectionWithImageAndProductsDTO collectionDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "collectionDTO.imageDTO", target = "collection.image")
    @Mapping(source = "collectionDTO.productDTOs", target = "collection.products")
    Category updateFromCollectionDTO(CollectionWithImageAndProductsDTO collectionDTO, @MappingTarget Category collection);
}
