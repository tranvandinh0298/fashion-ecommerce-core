package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.attribute;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeAndOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface AttributeAndOptionsMapper extends AttributeMapper{
    @Mapping(source = "options", target = "optionDTOs")
    AttributeAndOptionsDTO mapToAttributeDTO(Attribute attribute);

    @Mapping(source = "optionDTOs", target = "options")
    Attribute mapToAttribute(AttributeAndOptionsDTO attributeAndOptionsDTO);

    Attribute updateFromAttributeDTO(AttributeAndOptionsDTO attributeAndOptionsDTO, @MappingTarget Attribute attribute);
}
