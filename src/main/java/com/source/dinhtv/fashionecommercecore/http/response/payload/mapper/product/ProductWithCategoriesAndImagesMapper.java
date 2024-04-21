package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithCategoriesAndImagesDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface ProductWithCategoriesAndImagesMapper extends ProductWithCategoriesMapper{
    @Mapping(source = "imageDTOs", target = "images")
    Product mapToProduct(ProductWithCategoriesAndImagesDTO productDTO);

    @Mapping(source = "images", target = "imageDTOs")
    ProductWithCategoriesAndImagesDTO mapToProductDTO(Product product);
}
