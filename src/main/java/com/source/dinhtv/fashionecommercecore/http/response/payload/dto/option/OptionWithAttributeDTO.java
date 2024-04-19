package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeAndOptionsDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.attribute.AttributeDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OptionWithAttributeDTO extends OptionDTO{
    public AttributeDTO attributeDTO;
}
