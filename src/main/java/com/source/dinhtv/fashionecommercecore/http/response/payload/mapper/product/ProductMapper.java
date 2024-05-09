package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.*;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.banner.BannerMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.brand.BrandMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.sku.SkuMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.image.ImageMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import com.source.dinhtv.fashionecommercecore.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BrandMapper.class})
public interface ProductMapper {
    // Mapping for sole Product
    @Mapping(source = "id", target = "productId")
    ProductDTO mapToProductDTO(Product product);

    @Mapping(source = "productId", target = "id")
    Product mapToProduct(ProductDTO productDTO);

    @Mapping(target = "id", ignore = true)
    void updateProduct(Product incomingProduct, @MappingTarget Product existedProduct);

    // Mapping for Product with Parent Product
    @Mapping(source = "id", target = "productId")
    @Mapping(source = "category", target = "categoryDTO", qualifiedByName = "mapToCategoryDTO")
    @Mapping(source = "childCategory", target = "childCategoryDTO", qualifiedByName = "mapToCategoryDTO")
    @Mapping(source = "brand", target = "brandDTO", qualifiedByName = "mapToBrandDTO")
    ProductWithCategoriesAndBrandDTO mapToProductWithCategoriesAndBrandDTO(Product product);

//    @Named("mapParentProduct")
//    default ProductDTO mapParentProduct(Product parentProduct) {
//        return !Objects.isNull(parentProduct) ? mapToProductDTO(parentProduct) : null;
//    }

    
}
