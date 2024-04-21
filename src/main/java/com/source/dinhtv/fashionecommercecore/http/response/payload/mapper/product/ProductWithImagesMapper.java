package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithImagesDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface ProductWithImagesMapper extends ProductMapper{
    @Mapping(source = "imageDTOs", target = "images")
    Product mapToProduct(ProductWithImagesDTO productDTO);

    @Mapping(source = "images", target = "imageDTOs")
    ProductWithImagesDTO mapToProductDTO(Product product);
}
