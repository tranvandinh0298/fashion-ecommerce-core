package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionWithAttributeDTO;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface OptionWithAttributeMapper extends OptionMapper{
    @Mapping(source = "attribute", target = "attributeDTO")
    OptionWithAttributeDTO mapOptionDTO(Option option);

    @Mapping(source = "attributeDTO", target = "attribute")
    Option mapToOption(OptionWithAttributeDTO optionDTO);

    @Mapping(target = "id", ignore = true)
    Option updateFromOptionDTO(OptionWithAttributeDTO optionDTO, @MappingTarget Option option);
}
