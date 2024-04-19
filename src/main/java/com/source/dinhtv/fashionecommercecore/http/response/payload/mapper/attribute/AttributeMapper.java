package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.attribute;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeAndOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option.AttributeOptionMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AttributeOptionMapper.class})
public interface AttributeMapper {
    @Mapping(source = "id", target = "attributeId")
    @Mapping(source = "options", target = "optionDTOs")
    AttributeAndOptionsDTO mapToAttributeDTO(Attribute attribute);

    @Mapping(source = "attributeId", target = "id")
    @Mapping(source = "optionDTOs", target = "options")
    Attribute mapToAttribute(AttributeAndOptionsDTO attributeAndOptionsDTO);

    @Mapping(target = "id", ignore = true)
    Attribute updateFromAttributeDTO(AttributeAndOptionsDTO attributeAndOptionsDTO, @MappingTarget Attribute attribute);
}
