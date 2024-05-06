package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.user.UserDTO;
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
public class CategoryDTO extends BaseDTO {
    public Integer categoryId;
    public String title;
    public String slug;
    public String summary;
    public String photo;
    public String isParent;
    public String status;
}
