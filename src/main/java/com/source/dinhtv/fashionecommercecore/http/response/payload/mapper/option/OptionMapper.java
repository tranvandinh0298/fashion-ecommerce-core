package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionDTO;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {Attribute.class})
public interface OptionMapper {
    @Mapping(source = "id", target = "optionId")
    OptionDTO mapOptionDTO(Option option);

    @Mapping(source = "optionId", target = "id")
    Option mapToOption(OptionDTO optionDTO);

    @Mapping(target = "id", ignore = true)
    Option updateFromOptionDTO(OptionDTO optionDTO, @MappingTarget Option option);
}
