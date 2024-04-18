package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.CategoryController;
import com.source.dinhtv.fashionecommercecore.http.controller.ProductController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.ProductDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.ProductMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.Product;
import com.source.dinhtv.fashionecommercecore.model.Sku;
import com.source.dinhtv.fashionecommercecore.repository.ProductRepository;
import com.source.dinhtv.fashionecommercecore.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.combineSpecs;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.isNonDeletedRecord;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkuRepository skuRepository;
    @Autowired
    private ProductMapper productMapper;

    public BaseResponse getAllProducts(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Product> specs = combineSpecs(List.of(
            isNonDeletedRecord()
        ));
        Page<Product> productsPage = productRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (productsPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm nào");
        }

        List<EntityModel<ProductDTO>> productEntities = productsPage.stream().map(
                product -> EntityModel.of(
                        productMapper.mapToProductDTO(product),
                        linkTo(methodOn(ProductController.class).getProductById(product.getId())).withSelfRel()
                )
        ).toList();

        PagedModel<EntityModel<ProductDTO>> pagedModel = getPagedModel(productEntities, pageNum, pageSize, productsPage.getTotalElements(), productsPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getProductById(int id) {

        return new SuccessResponse();
    }

    public BaseResponse createProduct(ProductDTO productDTO) {
        Product product = productMapper.mapToProduct(productDTO);

        productRepository.save(product);

        List<Sku> skus = product.getSkus();
        skus.forEach(sku -> sku.setProduct(product));
        skuRepository.saveAll(skus);

        return new SuccessResponse(productMapper.mapToProductDTO(product));
    }


}
