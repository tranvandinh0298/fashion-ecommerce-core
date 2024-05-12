package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.model.PostCategory;
import com.source.dinhtv.fashionecommercecore.model.PostTag;
import com.source.dinhtv.fashionecommercecore.model.User;
import jakarta.persistence.*;
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
public class PostDTO extends BaseDTO {
    public Integer postId;
    public String title;
    public String slug;
    public String summary;
    public String description;
    public String quote;
    public String photo;
    public String tags;
    public Integer postCategoryId;
    public Integer postTagId;
    public Integer userId;
    public String status;
}
