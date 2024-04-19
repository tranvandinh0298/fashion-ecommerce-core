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
    @Mapping(source = "skuDTOs", target = "skus")
    @Mapping(source = "imageDTOs", target = "images")
    @Mapping(source = "categoryDTOs", target = "categories")
    Product mapToProduct(ProductDTO productDTO);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "skus", target = "skuDTOs")
    @Mapping(source = "images", target = "imageDTOs")
    @Mapping(source = "categories", target = "categoryDTOs")
    ProductDTO mapToProductDTO(Product product);
}
