package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.tag;

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
public class PostTagDTO extends BaseDTO {
    public Integer postTagId;
    public String title;
    public String slug;
    public String status;
}
