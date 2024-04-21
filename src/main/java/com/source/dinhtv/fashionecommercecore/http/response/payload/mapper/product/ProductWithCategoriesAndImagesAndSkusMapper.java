package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithCategoriesAndImagesAndSkusDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface ProductWithCategoriesAndImagesAndSkusMapper extends ProductWithCategoriesAndImagesMapper{
    @Mapping(source = "skuDTOs", target = "skus")
    Product mapToProduct(ProductWithCategoriesAndImagesAndSkusDTO productDTO);

    @Mapping(source = "skus", target = "skuDTOs")
    ProductWithCategoriesAndImagesAndSkusDTO mapToProductDTO(Product product);
}
