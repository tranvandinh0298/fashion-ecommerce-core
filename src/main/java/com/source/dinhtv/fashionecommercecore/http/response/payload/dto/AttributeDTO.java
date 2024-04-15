package com.source.dinhtv.fashionecommercecore.http.response.payload.dto;

import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AttributeDTO {
    public Integer id;
    public String name;
    public List<AttributeOptionDTO> optionDTOS;
}
