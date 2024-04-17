package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AttributeMapper.class})
public interface AttributeOptionMapper {
    AttributeOptionDTO mapToAttributeOptionDTO(AttributeOption attributeOption);

    AttributeOption mapToAttributeOption(AttributeOptionDTO attributeOptionDTO);

    @Mapping(target = "id", ignore = true)
    AttributeOption updateFromAttributeOptionDTO(AttributeOptionDTO attributeOptionDTO, @MappingTarget AttributeOption attributeOption);
}
