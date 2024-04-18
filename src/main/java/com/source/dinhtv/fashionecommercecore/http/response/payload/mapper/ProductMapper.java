package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ProductDTO;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SkuMapper.class})
public interface ProductMapper {
    @Mapping(source = "skuDTOs", target = "skus")
    @Mapping(source = "imageDTOs", target = "images")
    Product mapToProduct(ProductDTO productDTO);

    @Mapping(source = "skus", target = "skuDTOs")
    @Mapping(source = "images", target = "imageDTOs")
    ProductDTO mapToProductDTO(Product product);
}
