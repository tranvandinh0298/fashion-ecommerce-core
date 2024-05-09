package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.ProductController;
import com.source.dinhtv.fashionecommercecore.http.controller.ProductController;
import com.source.dinhtv.fashionecommercecore.http.request.ProductFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.ProductFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.product.ProductWithCategoriesAndBrandDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.product.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Product;
import com.source.dinhtv.fashionecommercecore.model.Product;
import com.source.dinhtv.fashionecommercecore.repository.ProductRepository;
import com.source.dinhtv.fashionecommercecore.repository.specification.DynamicalSpecification;
import com.source.dinhtv.fashionecommercecore.repository.specification.SearchSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.CustomConstants.IS_NOT_PARENT_CATEGORY;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    public BaseResponse getAllProducts(SearchRequest request) {
        // filter object
        BaseFilter productFilter = new ProductFilter(request);
        productFilter.convertFilterKey();
        productFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Product> specs = new DynamicalSpecification<>(request, productFilter.getAdditionalSpecs());

        // page with products
        Page<Product> productPage = productRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        // convert entities to DTOs
        List<EntityModel<ProductWithCategoriesAndBrandDTO>> ProductEntities = productPage.stream().map(
                product -> EntityModel.of(
                        productMapper.mapToProductWithCategoriesAndBrandDTO(product),
                        linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel())
        ).toList();

        // page with productDTOs
        PagedModel<EntityModel<ProductWithCategoriesAndBrandDTO>> pagedModel = getPagedModel(ProductEntities, productPage.getNumber(), productPage.getSize(), productPage.getTotalElements(), productPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllProductsWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter productFilter = new ProductFilter(request);
        productFilter.convertFilterKey();
        productFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Product> specs = new DynamicalSpecification<>(request, productFilter.getAdditionalSpecs());

        // products
        List<Product> products = productRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<ProductWithCategoriesAndBrandDTO>> ProductEntities = products.stream().map(
                product -> EntityModel.of(
                        productMapper.mapToProductWithCategoriesAndBrandDTO(product),
                        linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(ProductEntities);
    }
    
    public BaseResponse getProductById(int id) {
        Product existedProduct = findByIdOrThrowEx(id);

        ProductWithCategoriesAndBrandDTO productDTO = productMapper.mapToProductWithCategoriesAndBrandDTO(existedProduct);

        Link allCategoriesLink = linkTo(methodOn(ProductController.class).getAllProducts(new SearchRequest())).withRel("allProducts");

        EntityModel<ProductWithCategoriesAndBrandDTO> productEntity = EntityModel.of(productDTO, allCategoriesLink);

        return new SuccessResponse(productEntity);
    }

    public BaseResponse createProduct(ProductDTO productDTO) {
        Product incomingProduct = productMapper.mapToProduct(productDTO);

        if (!Objects.isNull(productDTO.getCategoryId())) {
            incomingProduct.setCategory(categoryService.findByIdOrThrowEx(productDTO.getCategoryId()));
        }

        if (!Objects.isNull(productDTO.getChildCategoryId())) {
            incomingProduct.setChildCategory(categoryService.findByIdOrThrowEx(productDTO.getChildCategoryId()));
        }

        if (!Objects.isNull(productDTO.getBrandId())) {
            incomingProduct.setBrand(brandService.findByIdOrThrowEx(productDTO.getBrandId()));
        }

        productRepository.save(incomingProduct);

        return new SuccessResponse(productMapper.mapToProductDTO(incomingProduct));
    }

    public BaseResponse updateProduct(int id, ProductDTO productDTO) {
        Product existedProduct = findByIdOrThrowEx(id);

        Product incomingProduct = productMapper.mapToProduct(productDTO);

        if (!Objects.isNull(productDTO.getCategoryId())) {
            incomingProduct.setCategory(categoryService.findByIdOrThrowEx(productDTO.getCategoryId()));
        }

        if (!Objects.isNull(productDTO.getChildCategoryId())) {
            incomingProduct.setChildCategory(categoryService.findByIdOrThrowEx(productDTO.getChildCategoryId()));
        }

        if (!Objects.isNull(productDTO.getBrandId())) {
            incomingProduct.setBrand(brandService.findByIdOrThrowEx(productDTO.getBrandId()));
        }

        productMapper.updateProduct(incomingProduct, existedProduct);

        productRepository.save(existedProduct);

        return new SuccessResponse(productMapper.mapToProductDTO(existedProduct));
    }

    public BaseResponse softDeleteProduct(int id) {
        Product existedProduct = findByIdOrThrowEx(id);

        existedProduct.softDelete();

        productRepository.save(existedProduct);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteProduct(int id) {
        productRepository.deleteById(id);

        return new SuccessResponse(true);
    }

    protected Product findByIdOrThrowEx(int id) {
        Specification<Product> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return this.productRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm cần tìm với id: " + id));
    }


}
