package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.option;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
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
public class OptionDTO extends BaseDTO {
    public Integer optionId;
    public String value;
}
