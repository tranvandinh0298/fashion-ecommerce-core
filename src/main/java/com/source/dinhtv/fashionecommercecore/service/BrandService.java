package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.BrandController;
import com.source.dinhtv.fashionecommercecore.http.controller.BrandController;
import com.source.dinhtv.fashionecommercecore.http.request.BrandFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BrandFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.brand.BrandDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.brand.BrandMapper;
import com.source.dinhtv.fashionecommercecore.model.Brand;
import com.source.dinhtv.fashionecommercecore.model.Brand;
import com.source.dinhtv.fashionecommercecore.repository.BrandRepository;
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

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;

    public BaseResponse getAllBrands(SearchRequest request) {
        new BrandFilter(request).convertFilterKey();

        SearchSpecification<Brand> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Brand> brandPage = brandRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<BrandDTO>> BrandEntities = brandPage.stream().map(
                brand -> EntityModel.of(
                        brandMapper.mapToBrandDTO(brand),
                        linkTo(methodOn(BrandController.class).getBrandById(brand.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<BrandDTO>> pagedModel = getPagedModel(BrandEntities, brandPage.getNumber(), brandPage.getSize(), brandPage.getTotalElements(), brandPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllBrandsWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter brandFilter = new BrandFilter(request);
        brandFilter.convertFilterKey();
        brandFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Brand> specs = new DynamicalSpecification<>(request, brandFilter.getAdditionalSpecs());

        // brands
        List<Brand> brands = brandRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<BrandDTO>> BrandEntities = brands.stream().map(
                brand -> EntityModel.of(
                        brandMapper.mapToBrandDTO(brand),
                        linkTo(methodOn(BrandController.class).getBrandById(brand.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(BrandEntities);
    }

    public BaseResponse getBrandById(int id) {
        Brand existedBrand = findByIdOrThrowEx(id);

        BrandDTO brandDTO = brandMapper.mapToBrandDTO(existedBrand);

        Link allBrandsLink = linkTo(methodOn(BrandController.class).getAllBrands(new SearchRequest())).withRel("allBrands");

        EntityModel<BrandDTO> brandEntity = EntityModel.of(brandDTO, allBrandsLink);

        return new SuccessResponse(brandEntity);
    }

    public BaseResponse createBrand(BrandDTO brandDTO) {
        Brand brand = brandMapper.mapToBrand(brandDTO);

        brandRepository.save(brand);

        return new SuccessResponse(brandMapper.mapToBrandDTO(brand));
    }

    public BaseResponse updateBrand(int id, BrandDTO brandDTO) {
        Brand existedBrand = findByIdOrThrowEx(id);

        brandMapper.updateFromBrandDTO(brandDTO, existedBrand);

        brandRepository.save(existedBrand);

        return new SuccessResponse(brandMapper.mapToBrandDTO(existedBrand));
    }

    public BaseResponse softDeleteBrand(int id) {
        Brand existedBrand = findByIdOrThrowEx(id);

        existedBrand.softDelete();

        brandRepository.save(existedBrand);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteBrand(int id) {
        brandRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected Brand findByIdOrThrowEx(int id) {
        Specification<Brand> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return brandRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy brand cần tìm với id: " + id));
    }
}
