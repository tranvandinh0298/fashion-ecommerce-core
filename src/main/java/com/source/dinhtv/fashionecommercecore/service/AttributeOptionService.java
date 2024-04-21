package com.source.dinhtv.fashionecommercecore.service;

import com.source.dinhtv.fashionecommercecore.exception.ResourceNotFoundException;
import com.source.dinhtv.fashionecommercecore.http.controller.AttributeController;
import com.source.dinhtv.fashionecommercecore.http.controller.AttributeOptionController;
import com.source.dinhtv.fashionecommercecore.http.response.BaseResponse;
import com.source.dinhtv.fashionecommercecore.http.response.SuccessResponse;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.attribute.AttributeMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.Option;
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
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.getPagedModel;
import static com.source.dinhtv.fashionecommercecore.utils.PaginationUtil.verifyPageNumAndSize;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AttributeOptionService {
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private AttributeMapper attributeMapper;
    @Autowired
    private AttributeOptionRepository optionRepository;
    @Autowired
    private OptionMapper optionMapper;

    public BaseResponse getAllAttributeOptions(int attributeId, int pageNum, int pageSize) {
        verifyPageNumAndSize(pageNum,pageSize);

        Specification<Option> specs = combineSpecs(List.of(
                withAttributeId(attributeId),
                withNonDeletedAttribute(),
                isNonDeletedRecord()
        ));
        Page<Option> optionsPage = optionRepository.findAll(specs, PageRequest.of(pageNum, pageSize));

        if (optionsPage.isEmpty()) {
            throw new ResourceNotFoundException("Không tìm thấy giá trị tùy chọn nào");
        }

        List<EntityModel<OptionDTO>> optionEntities = optionsPage.stream().map(
                option -> EntityModel.of(
                        optionMapper.mapOptionDTO(option),
                        linkTo(methodOn(AttributeOptionController.class).getAttributeOptionById(attributeId, option.getId())).withSelfRel())
        ).toList();

        PagedModel<EntityModel<OptionDTO>> pagedModel = getPagedModel(optionEntities,pageNum,pageSize, optionsPage.getTotalElements(), optionsPage.getTotalPages());

        Link attributeLink = linkTo(methodOn(AttributeController.class).getAttributeById(attributeId)).withRel("attribute");

        pagedModel.add(attributeLink);

        return new SuccessResponse(pagedModel);
    }

    public BaseResponse getAttributeOptionById(int attributeId, int optionId) {
        Option option = findByIdOrThrowEx(attributeId, optionId);
        
        OptionDTO optionDTO = optionMapper.mapOptionDTO(option);

        Link allAttributesLink = linkTo(methodOn(AttributeController.class).getAllAttributes(0,10)).withRel("allAttributes");
        Link attributeLink = linkTo(methodOn(AttributeController.class).getAttributeById(attributeId)).withRel("attribute");
        Link allAttributeOptionsLink = linkTo(methodOn(AttributeOptionController.class).getAllAttributeOptions(attributeId, 0, 10)).withRel("allAttributeOptionsLink");

        EntityModel<OptionDTO> attributeEntity = EntityModel.of(optionDTO, allAttributesLink, attributeLink, allAttributeOptionsLink);

        return new SuccessResponse(attributeEntity);
    }

    public BaseResponse createAttributeOption(int attributeId, OptionDTO optionDTO) {
        Option option = optionMapper.mapToOption(optionDTO);

        Attribute attribute = this.attributeRepository.findOne(combineSpecs(List.of(
                hasId(attributeId),
                isNonDeletedRecord()
        ))).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuộc tính cần tìm với id: " + attributeId));

        option.setAttribute(attribute);

        optionRepository.save(option);

        return new SuccessResponse(optionMapper.mapOptionDTO(option));
    }

    public BaseResponse updateAttributeOption(int attributeId, int optionId, OptionDTO optionDTO) {
        Option option = findByIdOrThrowEx(attributeId, optionId);

        optionMapper.updateFromOptionDTO(optionDTO, option);

        optionRepository.save(option);

        return new SuccessResponse(optionMapper.mapOptionDTO(option));
    }

    public BaseResponse softDeleteAttributeOption(int attributeId, int optionId) {
        Option option = findByIdOrThrowEx(attributeId, optionId);

        option.softDelete();

        optionRepository.save(option);

        return new SuccessResponse();
    }

    public BaseResponse deleteAttributeOption(int attributeId, int optionId) {
        Option option = findByIdOrThrowEx(attributeId, optionId);

        optionRepository.deleteById(optionId);

        return new SuccessResponse();
    }

    private Option findByIdOrThrowEx(int attributeId, int optionId) {
        Specification<Option> spec = combineSpecs(List.of(
                withAttributeId(attributeId),
                withNonDeletedAttribute(),
                hasId(optionId),
                isNonDeletedRecord()
        ));
        return optionRepository.findOne(spec).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy giá trị tùy chọn cần tìm với id: " + optionId));
    }
}
