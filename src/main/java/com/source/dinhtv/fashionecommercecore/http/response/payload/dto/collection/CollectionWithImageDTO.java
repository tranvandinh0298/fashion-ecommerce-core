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
public class CollectionWithImageDTO extends CollectionDTO{
    public ImageDTO imageDTO;
}
