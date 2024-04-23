package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.*;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SkuMapper.class, ImageMapper.class, CategoryMapper.class})
public interface ProductMapper {
    /**
     * only Product
     * */
    @Mapping(source = "productId", target = "id")
    Product mapToProduct(ProductDTO productDTO);

    @Mapping(source = "id", target = "productId")
    ProductDTO mapToProductDTO(Product product);

    /**
     * Product & Category
     * */
    @Mapping(source = "productId", target = "id")
    @Mapping(source = "categoryDTOs", target = "categories")
    Product mapToProduct(ProductWithCategoriesDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "categories", target = "categoryDTOs")
    ProductWithCategoriesDTO mapToProductAndCategoryDTO(Product product);

    /**
     * Product & Images
     * */
    @Mapping(source = "productId", target = "id")
    @Mapping(source = "imageDTOs", target = "images")
    Product mapToProduct(ProductWithImagesDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "images", target = "imageDTOs")
    ProductWithImagesDTO mapToProductAndImageDTO(Product product);

    /**
     * Product & Skus
     * */
    @Mapping(source = "productId", target = "id")
    @Mapping(source = "skuDTOs", target = "skus")
    Product mapToProduct(ProductWithSkusDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "skus", target = "skuDTOs")
    ProductWithSkusDTO mapToProductAndSkusDTO(Product product);

    /**
     * Product & Images & Skus
     * */
    @Mapping(source = "productId", target = "id")
    @Mapping(source = "imageDTOs", target = "images")
    @Mapping(source = "skuDTOs", target = "skus")
    Product mapToProduct(ProductWithImagesAndSkusDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "skus", target = "skuDTOs")
    @Mapping(source = "images", target = "imageDTOs")
    ProductWithImagesAndSkusDTO mapToProductAndImagesAndSkusDTO(Product product);

    /**
     * Product & Category & Images & Skus
     * */
    @Mapping(source = "productId", target = "id")
    @Mapping(source = "categoryDTOs", target = "categories")
    @Mapping(source = "imageDTOs", target = "images")
    @Mapping(source = "skuDTOs", target = "skus")
    Product mapToProduct(ProductWithCategoriesAndImagesAndSkusDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "categories", target = "categoryDTOs")
    @Mapping(source = "images", target = "imageDTOs")
    @Mapping(source = "skus", target = "skuDTOs")
    ProductWithCategoriesAndImagesAndSkusDTO mapToProductAndCategoriesAndImagesAndSkusDTO(Product product);


}
