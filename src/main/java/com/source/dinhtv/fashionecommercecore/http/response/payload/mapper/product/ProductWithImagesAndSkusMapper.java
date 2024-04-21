package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithImagesAndSkusDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithImagesDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface ProductWithImagesAndSkusMapper extends ProductWithImagesMapper{
    @Mapping(source = "skuDTOs", target = "skus")
    Product mapToProduct(ProductWithImagesAndSkusDTO productDTO);

    @Mapping(source = "skus", target = "skuDTOs")
    ProductWithImagesAndSkusDTO mapToProductDTO(Product product);
}
