package com.source.dinhtv.fashionecommercecore.http.response.payload.dto;

import com.source.dinhtv.fashionecommercecore.model.Sku;
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
public class ProductDTO {
    public Integer id;

    public String name;

    public String slug;

    public String code;

    public String description;

    public Integer status;

    public CategoryDTO categoryDTO;

    public List<SkuDTO> skuDTOs;

    public List<ImageDTO> imageDTOs;
}
