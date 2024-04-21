package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithCategoriesDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface ProductWithCategoriesMapper extends ProductMapper{
    @Mapping(source = "categoryDTOs", target = "categories")
    Product mapToProduct(ProductWithCategoriesDTO productDTO);

    @Mapping(source = "categories", target = "categoryDTOs")
    ProductWithCategoriesDTO mapToProductDTO(Product product);
}
