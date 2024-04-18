package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.AttributeController;
import com.source.dinhtv.fashionecommercecore.http.controller.ImageController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.AttributeMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import com.source.dinhtv.fashionecommercecore.repository.AttributeOptionRepository;
import com.source.dinhtv.fashionecommercecore.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.AttributeOptionRepository.withAttributeId;
import static com.source.dinhtv.fashionecommercecore.repository.AttributeOptionRepository.withNonDeletedAttribute;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.isNonDeletedRecord;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeOptionRepository optionRepository;
    @Autowired
    private AttributeMapper attributeMapper;

    public BaseResponse getAllAttributes(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Attribute> specs = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        Page<Attribute> attributesPage = attributeRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (attributesPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy thuộc tính nào");
        }

        List<EntityModel<AttributeDTO>> attributeEntities = attributesPage.stream().map(
                attribute -> EntityModel.of(
                        attributeMapper.mapToAttributeDTO(attribute),
                        linkTo(methodOn(AttributeController.class).getAttributeById(attribute.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<AttributeDTO>> pagedModel = getPagedModel(attributeEntities,pageNum,pageSize, attributesPage.getTotalElements(), attributesPage.getTotalPages());

        return new SuccessResponse(pagedModel);

    }

    public BaseResponse getAttributeById(int id) {
        Attribute attribute = findByIdOrThrowEx(id);

        AttributeDTO attributeDTO = attributeMapper.mapToAttributeDTO(attribute);

        Link allAttributesLink = linkTo(methodOn(AttributeController.class).getAllAttributes(0,10)).withRel("allAttributes");

        EntityModel<AttributeDTO> attributeEntity = EntityModel.of(attributeDTO, allAttributesLink);

        return new SuccessResponse(attributeEntity);
    }

    public BaseResponse createAttribute(AttributeDTO attributeDTO) {
        Attribute attribute = attributeMapper.mapToAttribute(attributeDTO);

        attributeRepository.save(attribute);

        return new SuccessResponse(attributeMapper.mapToAttributeDTO(attribute));
    }

    public BaseResponse updateAttribute(int id, AttributeDTO attributeDTO) {
        Attribute existedAttribute = findByIdOrThrowEx(id);

        attributeMapper.updateFromAttributeDTO(attributeDTO, existedAttribute);

        attributeRepository.save(existedAttribute);

        return new SuccessResponse(attributeMapper.mapToAttributeDTO(existedAttribute));
    }

    public BaseResponse softDeleteAttribute(int id) {
        Attribute existingAttribute = findByIdOrThrowEx(id);

        existingAttribute.softDelete();

        attributeRepository.save(existingAttribute);

        return new SuccessResponse();
    }

    public BaseResponse deleteAttribute(int id) {
        attributeRepository.deleteById(id);

        return new SuccessResponse();
    }

    private Attribute findByIdOrThrowEx(int id) {
        Specification<Attribute> spec = combineSpecs(List.of(
                hasId(id),
                isNonDeletedRecord()
        ));
        return this.attributeRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + id));
    }
}
