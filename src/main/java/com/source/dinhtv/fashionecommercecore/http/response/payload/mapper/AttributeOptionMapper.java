package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.AttributeOptionDTO;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AttributeMapper.class})
public interface AttributeOptionMapper {
    AttributeOptionDTO mapToAttributeOptionDTO(AttributeOption attributeOption);

    AttributeOption mapToAttributeOption(AttributeOptionDTO attributeOptionDTO);
}
