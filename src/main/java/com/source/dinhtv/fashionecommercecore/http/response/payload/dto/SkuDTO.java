package com.source.dinhtv.fashionecommercecore.http.response.payload.dto;

import com.source.dinhtv.fashionecommercecore.model.AttributeOption;
import com.source.dinhtv.fashionecommercecore.model.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class SkuDTO {
    public Integer id;
    public String code;
    public Integer quantity;
    public Double price;
    public List<AttributeOptionDTO> optionDTOs;
}
