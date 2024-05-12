package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.PostDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.PostWithCategoryAndUserAndTagDTO;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.category.PostCategoryMapper;
import com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.tag.PostTagMapper;
import com.source.dinhtv.fashionecommercecore.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {PostCategoryMapper.class, PostTagMapper.class})
public interface PostMapper {
    /**
     * Only Post
     * */
    @Named("mapToPostDTO")
    @Mapping(source = "id", target = "postId")
    PostDTO mapToPostDTO(Post post);

    @Named("mapToPost")
    @Mapping(source = "postId", target = "id")
    Post mapToPost(PostDTO postDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    void updateFromPostDTO(PostDTO postDTO, @MappingTarget Post post);

    @Mapping(source = "id", target = "postId")
    @Mapping(source = "postCategory", target = "postCategoryDTO")
    @Mapping(source = "user", target = "userDTO")
    PostWithCategoryAndUserAndTagDTO mapToPostWithCategoryAndUserAndTagDTO(Post post);
}
