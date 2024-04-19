package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionDTO;
import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttributeOptionMapper {
    @Mapping(source = "id", target = "optionId")
    OptionDTO mapToAttributeOptionDTO(AttributeOption attributeOption);

    @Mapping(source = "optionId", target = "id")
    AttributeOption mapToAttributeOption(OptionDTO optionDTO);

    @Mapping(target = "id", ignore = true)
    AttributeOption updateFromAttributeOptionDTO(OptionDTO optionDTO, @MappingTarget AttributeOption attributeOption);
}
