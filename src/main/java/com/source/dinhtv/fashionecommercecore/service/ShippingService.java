package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.ShippingController;
import com.source.dinhtv.fashionecommercecore.http.request.ShippingFilter;
import com.source.dinhtv.fashionecommercecore.http.request.BaseFilter;
import com.source.dinhtv.fashionecommercecore.http.request.search.SearchRequest;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.shipping.ShippingDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.shipping.ShippingMapper;
import com.source.dinhtv.fashionecommercecore.model.Shipping;
import com.source.dinhtv.fashionecommercecore.repository.ShippingRepository;
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
public class ShippingService {
    @Autowired
    private ShippingRepository shippingRepository;
    @Autowired
    private ShippingMapper shippingMapper;

    public BaseResponse getAllShippings(SearchRequest request) {
        new ShippingFilter(request).convertFilterKey();

        SearchSpecification<Shipping> specs = new DynamicalSpecification<>(request, List.of(isNonDeletedRecord()));

        Page<Shipping> shippingPage = shippingRepository.findAll(specs, PageRequest.of(request.getPage(), request.getSize()));

        List<EntityModel<ShippingDTO>> ShippingEntities = shippingPage.stream().map(
                shipping -> EntityModel.of(
                        shippingMapper.mapToShippingDTO(shipping),
                        linkTo(methodOn(ShippingController.class).getShippingById(shipping.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<ShippingDTO>> pagedModel = getPagedModel(ShippingEntities, shippingPage.getNumber(), shippingPage.getSize(), shippingPage.getTotalElements(), shippingPage.getTotalPages());

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAllShippingsWithoutPagination(SearchRequest request) {
        // filter object
        BaseFilter shippingFilter = new ShippingFilter(request);
        shippingFilter.convertFilterKey();
        shippingFilter.appendSpecs(isNonDeletedRecord());

        // specs
        SearchSpecification<Shipping> specs = new DynamicalSpecification<>(request, shippingFilter.getAdditionalSpecs());

        // shippings
        List<Shipping> shippings = shippingRepository.findAll(specs);

        // convert entities to DTOs
        List<EntityModel<ShippingDTO>> ShippingEntities = shippings.stream().map(
                shipping -> EntityModel.of(
                        shippingMapper.mapToShippingDTO(shipping),
                        linkTo(methodOn(ShippingController.class).getShippingById(shipping.getId())).withSelfRel())
        ).toList();

        return new SuccessResponse(ShippingEntities);
    }

    public BaseResponse getShippingById(int id) {
        Shipping existedShipping = findByIdOrThrowEx(id);

        ShippingDTO shippingDTO = shippingMapper.mapToShippingDTO(existedShipping);

        Link allShippingsLink = linkTo(methodOn(ShippingController.class).getAllShippings(new SearchRequest())).withRel("allShippings");

        EntityModel<ShippingDTO> shippingEntity = EntityModel.of(shippingDTO, allShippingsLink);

        return new SuccessResponse(shippingEntity);
    }

    public BaseResponse createShipping(ShippingDTO shippingDTO) {
        Shipping shipping = shippingMapper.mapToShipping(shippingDTO);

        shippingRepository.save(shipping);

        return new SuccessResponse(shippingMapper.mapToShippingDTO(shipping));
    }

    public BaseResponse updateShipping(int id, ShippingDTO shippingDTO) {
        Shipping existedShipping = findByIdOrThrowEx(id);

        shippingMapper.updateFromShippingDTO(shippingDTO, existedShipping);

        shippingRepository.save(existedShipping);

        return new SuccessResponse(shippingMapper.mapToShippingDTO(existedShipping));
    }

    public BaseResponse softDeleteShipping(int id) {
        Shipping existedShipping = findByIdOrThrowEx(id);

        existedShipping.softDelete();

        shippingRepository.save(existedShipping);

        return new SuccessResponse(true);
    }

    public BaseResponse deleteShipping(int id) {
        shippingRepository.deleteById(id);

        return new SuccessResponse();
    }

    protected Shipping findByIdOrThrowEx(int id) {
        Specification<Shipping> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return shippingRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy shipping cần tìm với id: " + id));
    }
}
