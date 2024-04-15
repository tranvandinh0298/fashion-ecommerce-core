package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.AttributeController;
import com.source.dinhtv.fashionecommercecore.http.controller.ImageController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.AttributeMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
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
    private AttributeMapper attributeMapper;

    public BaseResponse getAllAttributes(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Attribute> specs = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        Page<Attribute> categoriesPage = attributeRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (categoriesPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy thuộc tính nào");
        }

        List<EntityModel<AttributeDTO>> attributeEntities = categoriesPage.stream().map(
                attribute -> EntityModel.of(
                        attributeMapper.mapToAttributeDTO(attribute),
                        linkTo(methodOn(AttributeController.class).getAttributeById(attribute.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<AttributeDTO>> pagedModel = getPagedModel(attributeEntities,pageNum,pageSize, categoriesPage.getTotalElements(), categoriesPage.getTotalPages());

        return new SuccessResponse(pagedModel);

    }

    public BaseResponse getAttributeById(Integer id) {
        Specification<Attribute> spec = combineSpecs(List.of(
                hasId(id), isNonDeletedRecord()
        ));
        Attribute attribute = this.attributeRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + id));

        AttributeDTO attributeDTO = attributeMapper.mapToAttributeDTO(attribute);

        Link allCategoriesLink = linkTo(methodOn(ImageController.class).getAllImages(0,10)).withRel("allImages");

        EntityModel<AttributeDTO> attributeEntity = EntityModel.of(attributeDTO, allCategoriesLink);

        return new SuccessResponse(attributeEntity);
    }

    public BaseResponse createAttribute(AttributeDTO attributeDTO) {
        Attribute attribute = attributeMapper.mapToAttribute(attributeDTO);

        attributeRepository.save(attribute);

        return new SuccessResponse(attributeMapper.mapToAttributeDTO(attribute));
    }

    public BaseResponse updateAttribute(Integer id, AttributeDTO attributeDTO) {
        Attribute existedattribute = attributeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + id));

        attributeMapper.updateFromAttributeDTO(attributeDTO, existedattribute);

        attributeRepository.save(existedattribute);

        return new SuccessResponse(attributeMapper.mapToAttributeDTO(existedattribute));
    }

    public BaseResponse softDeleteAttribute(Integer id) {
        Attribute existingAttribute = attributeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + id));

        existingAttribute.softDelete();

        attributeRepository.save(existingAttribute);

        return new SuccessResponse();
    }

    public BaseResponse deleteAttribute(Integer id) {
        attributeRepository.deleteById(id);

        return new SuccessResponse();
    }
}
