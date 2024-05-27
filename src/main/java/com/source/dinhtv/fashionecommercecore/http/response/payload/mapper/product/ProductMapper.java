package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.*;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.brand.BrandMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.category.CategoryMapper;
import com.source.dinhtv.fashionecommercecore.model.Category;
import com.source.dinhtv.fashionecommercecore.model.Product;
import jdk.jfr.Name;
import org.mapstruct.*;

import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IMAGE_URL;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, BrandMapper.class})
public interface ProductMapper {
    // Mapping for sole Product
    @Named("mapToProductDTO")
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

    @AfterMapping
    default void addImageBaseUrl(Product product, @MappingTarget ProductDTO productDTO ) {
        String baseUrl = IMAGE_URL;
        if (product.getPhoto() != null) {
            productDTO.setPhoto(baseUrl + product.getPhoto());
        }
    }
    
}
