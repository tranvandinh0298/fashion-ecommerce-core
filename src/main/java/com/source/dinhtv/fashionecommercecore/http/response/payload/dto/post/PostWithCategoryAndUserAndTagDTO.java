package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.category.PostCategoryDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.tag.PostTagDTO;
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
public class PostWithCategoryAndUserAndTagDTO extends PostDTO {
    public PostCategoryDTO postCategoryDTO;
    public UserDTO userDTO;
}
