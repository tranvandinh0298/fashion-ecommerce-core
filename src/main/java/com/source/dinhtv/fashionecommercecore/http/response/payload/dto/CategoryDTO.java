package com.source.dinhtv.fashionecommercecore.http.response.payload.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
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
public class CategoryDTO {
    public Integer id;
    public String name;
    public String slug;
    public String description;
    public Integer status;
    public ImageDTO imageDTO;
}
