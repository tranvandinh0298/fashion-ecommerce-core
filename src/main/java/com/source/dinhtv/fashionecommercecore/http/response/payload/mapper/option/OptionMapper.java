package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.option;


import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option.OptionWithAttributeDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.attribute.AttributeMapper;
import com.source.dinhtv.fashionecommercecore.model.Attribute;
import com.source.dinhtv.fashionecommercecore.model.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {AttributeMapper.class})
public interface OptionMapper {
    /**
     * only Option
     * */
    @Named("mapToOptionDTO")
    @Mapping(source = "id", target = "optionId")
    OptionDTO mapToOptionDTO(Option option);

    @Named("mapToOption")
    @Mapping(source = "optionId", target = "id")
    Option mapToOption(OptionDTO optionDTO);

    @Named("updateFromOptionDTO")
    @Mapping(target = "id", ignore = true)
    void updateFromOptionDTO(OptionDTO optionDTO, @MappingTarget Option option);

    /**
     * Option & Attribute
     * */
//    @Mapping(source = "id", target = "optionId")
//    @Mapping(source = "attribute", target = "attributeDTO", qualifiedByName = "mapToAttributeDTO")
//    OptionWithAttributeDTO mapOptionAndAttributeDTO(Option option);
//
//    @Mapping(source = "optionId", target = "id")
//    @Mapping(source = "attributeDTO", target = "attribute", qualifiedByName = "mapToAttribute")
//    Option mapToOption(OptionWithAttributeDTO optionDTO);
//
//    @Mapping(target = "id", ignore = true)
//    void updateFromOptionDTO(OptionWithAttributeDTO optionDTO, @MappingTarget Option option);
}
