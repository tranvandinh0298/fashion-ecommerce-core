package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.comment;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.BaseDTO;
import com.source.dinhtv.fashionecommercecore.model.Post;
import com.source.dinhtv.fashionecommercecore.model.PostComment;
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
public class PostCommentDTO extends BaseDTO {
    public Integer id;
    public Integer userId;
    public Integer postId;
    public String comment;
    public String status;
    public String repliedComment;
    public Integer parentCommentId;
}
