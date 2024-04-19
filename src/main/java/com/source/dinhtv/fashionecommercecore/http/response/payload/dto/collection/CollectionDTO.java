package com.source.dinhtv.fashionecommercecore.http.response.payload.dto.collection;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.image.ImageDTO;
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
public class CollectionDTO {
    public Integer collectionId;
    public String name;
    public String slug;
    public String description;
    public Integer status;
    public ImageDTO imageDTO;
}
