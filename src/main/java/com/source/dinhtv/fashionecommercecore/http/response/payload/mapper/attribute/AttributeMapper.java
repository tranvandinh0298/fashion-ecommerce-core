package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.attribute;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeAndOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.OptionMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    /**
     * Only Attribute
     * */
    @Mapping(source = "id", target = "attributeId")
    AttributeDTO mapToAttributeDTO(Attribute attribute);

    @Mapping(source = "attributeId", target = "id")
    Attribute mapToAttribute(AttributeDTO attributeDTO);

    @Mapping(target = "id", ignore = true)
    Attribute updateFromAttributeDTO(AttributeDTO attributeDTO, @MappingTarget Attribute attribute);

    /**
     * Attribute & Options
     * */
    @Mapping(source = "id", target = "attributeId")
    @Mapping(source = "options", target = "optionDTOs")
    AttributeAndOptionsDTO mapToAttributeAndOptionsDTO(Attribute attribute);

    @Mapping(source = "attributeId", target = "id")
    @Mapping(source = "optionDTOs", target = "options")
    Attribute mapToAttribute(AttributeAndOptionsDTO attributeAndOptionsDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "attributeAndOptionsDTO.optionDTOs", target = "attribute.options")
    Attribute updateFromAttributeDTO(AttributeAndOptionsDTO attributeAndOptionsDTO, @MappingTarget Attribute attribute);
}
