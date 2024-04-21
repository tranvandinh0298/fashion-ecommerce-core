package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithCategoriesAndImagesDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithCategoriesAndSkusDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface ProductWithCategoriesAndSkusMapper extends ProductWithCategoriesMapper{
    @Mapping(source = "skuDTOs", target = "skus")
    Product mapToProduct(ProductWithCategoriesAndSkusDTO productDTO);

    @Mapping(source = "skus", target = "skuDTOs")
    ProductWithCategoriesAndSkusDTO mapToProductDTO(Product product);
}
