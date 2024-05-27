package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.banner;

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
public class BannerDTO extends BaseDTO {
    public Integer bannerId;
    public String title;
    public String slug;
    public String photo;
    public String description;
    public String link;
    public String status;
}