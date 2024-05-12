package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class PostCategoryDTO extends BaseDTO {
    public Integer postCategoryId;
    public String title;
    public String slug;
    public String status;
}
