package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SkuMapper.class, ImageMapper.class, CategoryMapper.class})
public interface ProductMapper {
    @Mapping(source = "productId", target = "id")
    Product mapToProduct(ProductDTO productDTO);

    @Mapping(source = "id", target = "productId")
    ProductDTO mapToProductDTO(Product product);
}
