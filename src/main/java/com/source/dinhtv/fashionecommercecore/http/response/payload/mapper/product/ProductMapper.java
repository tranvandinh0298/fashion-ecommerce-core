package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.*;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {SkuMapper.class, ImageMapper.class, CategoryMapper.class})
public interface ProductMapper {
    /**
     * only Product
     * */
    @Named("mapToProduct")
    @Mapping(source = "productId", target = "id")
    Product mapToProduct(ProductDTO productDTO);

    @Named("mapToProductDTO")
    @Mapping(source = "id", target = "productId")
    ProductDTO mapToProductDTO(Product product);

    /**
     * Product & Category
     * */
//    @Mapping(source = "productId", target = "id")
//    @Mapping(source = "categoryDTOs", target = "categories", qualifiedByName = "mapToCategory")
//    Product mapToProduct(ProductWithCategoriesDTO productDTO);
//
//    @Mapping(source = "id", target = "productId")
//    @Mapping(source = "categories", target = "categoryDTOs", qualifiedByName = "mapToCategoryDTO")
//    ProductWithCategoriesDTO mapToProductAndCategoryDTO(Product product);

    /**
     * Product & Images
     * */
//    @Mapping(source = "productId", target = "id")
//    @Mapping(source = "imageDTOs", target = "images", qualifiedByName = "mapToImage")
//    Product mapToProduct(ProductWithImagesDTO productDTO);
//
//    @Mapping(source = "id", target = "productId")
//    @Mapping(source = "images", target = "imageDTOs", qualifiedByName = "mapToImageDTO")
//    ProductWithImagesDTO mapToProductAndImageDTO(Product product);

    /**
     * Product & Skus
     * */
//    @Mapping(source = "productId", target = "id")
//    @Mapping(source = "skuDTOs", target = "skus", qualifiedByName = "mapToSku")
//    Product mapToProduct(ProductWithSkusDTO productDTO);
//
//    @Mapping(source = "id", target = "productId")
//    @Mapping(source = "skus", target = "skuDTOs", qualifiedByName = "mapToSkuDTO")
//    ProductWithSkusDTO mapToProductAndSkusDTO(Product product);

    /**
     * Product & Images & Skus
     * */
//    @Mapping(source = "productId", target = "id")
//    @Mapping(source = "imageDTOs", target = "images", qualifiedByName = "mapToImage")
//    @Mapping(source = "skuDTOs", target = "skus", qualifiedByName = "mapToSku")
//    Product mapToProduct(ProductWithImagesAndSkusDTO productDTO);
//
//    @Mapping(source = "id", target = "productId")
//    @Mapping(source = "skus", target = "skuDTOs", qualifiedByName = "mapToSkuDTO")
//    @Mapping(source = "images", target = "imageDTOs", qualifiedByName = "mapToImageDTO")
//    ProductWithImagesAndSkusDTO mapToProductAndImagesAndSkusDTO(Product product);

    /**
     * Product & Category & Images & Skus
     * */
//    @Mapping(source = "productId", target = "id")
//    @Mapping(source = "categoryDTOs", target = "categories", qualifiedByName = "mapToCategory")
//    @Mapping(source = "imageDTOs", target = "images", qualifiedByName = "mapToImage")
//    @Mapping(source = "skuDTOs", target = "skus", qualifiedByName = "mapToSku")
//    Product mapToProduct(ProductWithCategoriesAndImagesAndSkusDTO productDTO);
//
//    @Mapping(source = "id", target = "productId")
//    @Mapping(source = "categories", target = "categoryDTOs", qualifiedByName = "mapToCategoryDTO")
//    @Mapping(source = "images", target = "imageDTOs", qualifiedByName = "mapToImageDTO")
//    @Mapping(source = "skus", target = "skuDTOs", qualifiedByName = "mapToSkuDTO")
//    ProductWithCategoriesAndImagesAndSkusDTO mapToProductAndCategoriesAndImagesAndSkusDTO(Product product);


}
