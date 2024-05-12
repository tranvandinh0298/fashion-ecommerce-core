package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.tag;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.tag.PostTagDTO;
import com.source.dinhtv.fashionecommercecore.model.PostTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PostTagMapper {
    /**
     * Only PostTag
     * */
    @Named("mapToPostTagDTO")
    @Mapping(source = "id", target = "postTagId")
    PostTagDTO mapToPostTagDTO(PostTag postTag);

    @Named("mapToPostTag")
    @Mapping(source = "postTagId", target = "id")
    PostTag mapToPostTag(PostTagDTO postTagDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    void updateFromPostTagDTO(PostTagDTO postTagDTO, @MappingTarget PostTag postTag);
}
