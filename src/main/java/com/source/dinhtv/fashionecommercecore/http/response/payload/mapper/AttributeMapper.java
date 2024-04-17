package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.CategoryDTO;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AttributeOptionMapper.class})
public interface AttributeMapper {
    @Mapping(source = "options", target = "optionDTOs")
    AttributeDTO mapToAttributeDTO(Attribute attribute);

    @Mapping(source = "optionDTOs", target = "options")
    Attribute mapToAttribute(AttributeDTO attributeDTO);

    @Mapping(target = "id", ignore = true)
    Attribute updateFromAttributeDTO(AttributeDTO attributeDTO, @MappingTarget Attribute attribute);
}
