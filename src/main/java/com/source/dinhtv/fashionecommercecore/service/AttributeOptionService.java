package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.AttributeController;
import com.source.dinhtv.fashionecommercecore.http.controller.ImageController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.AttributeMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.AttributeOptionMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import com.source.dinhtv.fashionecommercecore.repository.AttributeOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;

import java.util.List;

import static com.source.dinhtv.fashionecommercecore.repository.specification.BaseSpecification.*;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AttributeOptionService {
    @Autowired
    private AttributeOptionRepository optionRepository;
    @Autowired
    private AttributeOptionMapper optionMapper;

    public List<EntityModel<AttributeOptionDTO>> getAllAttributeOptions(int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<AttributeOption> specs = combineSpecs(List.of(
                isNonDeletedRecord()
        ));
        Page<AttributeOption> optionsPage = optionRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (optionsPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy tùy chọn nào");
        }

        List<EntityModel<AttributeOptionDTO>> optionEntities = optionsPage.stream().map(
                option -> EntityModel.of(
                        optionMapper.mapToAttributeOptionDTO(option),
                        linkTo(methodOn(AttributeController.class).getAttributeById(option.getId())).withSelfRel())
        ).toList();

        return optionEntities;

    }

    public BaseResponse getAttributeOptionById(Integer id) {
        Specification<AttributeOption> spec = combineSpecs(List.of(
                hasId(id), isNonDeletedRecord()
        ));
        AttributeOption option = optionRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tùy chọn cần tìm với id: " + id));

        AttributeOptionDTO attributeOptionDTO = optionMapper.mapToAttributeOptionDTO(option);

        Link allCategoriesLink = linkTo(methodOn(ImageController.class).getAllImages(0,10)).withRel("allImages");

        EntityModel<AttributeDTO> attributeEntity = EntityModel.of(attributeDTO, allCategoriesLink);

        return new SuccessResponse(attributeEntity);
    }

    public BaseResponse createAttribute(AttributeDTO attributeDTO) {
        Attribute attribute = attributeMapper.mapToAttribute(attributeDTO);

        attributeOptionRepository.save(attribute);

        return new SuccessResponse(attributeMapper.mapToAttributeDTO(attribute));
    }

    public BaseResponse updateAttribute(Integer id, AttributeDTO attributeDTO) {
        Attribute existedattribute = attributeOptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + id));

        attributeMapper.updateFromAttributeDTO(attributeDTO, existedattribute);

        attributeOptionRepository.save(existedattribute);

        return new SuccessResponse(attributeMapper.mapToAttributeDTO(existedattribute));
    }

    public BaseResponse softDeleteAttribute(Integer id) {
        Attribute existingAttribute = attributeOptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + id));

        existingAttribute.softDelete();

        attributeOptionRepository.save(existingAttribute);

        return new SuccessResponse();
    }

    public BaseResponse deleteAttribute(Integer id) {
        attributeOptionRepository.deleteById(id);

        return new SuccessResponse();
    }
}
