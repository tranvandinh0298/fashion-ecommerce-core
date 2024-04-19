package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.option.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeOptionMapper {
    @Mapping(source = "id", target = "optionId")
    AttributeOptionDTO mapToAttributeOptionDTO(AttributeOption attributeOption);

    @Mapping(source = "optionId", target = "id")
    AttributeOption mapToAttributeOption(AttributeOptionDTO attributeOptionDTO);

    @Mapping(target = "id", ignore = true)
    AttributeOption updateFromAttributeOptionDTO(AttributeOptionDTO attributeOptionDTO, @MappingTarget AttributeOption attributeOption);
}
