package com.source.dinhtv.fashionecommercecore.http.response.payload.mapper.post.category;

import com.source.dinhtv.fashionecommercecore.http.response.payload.dto.post.category.PostCategoryDTO;
import com.source.dinhtv.fashionecommercecore.model.PostCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PostCategoryMapper {
    /**
     * Only PostCategory
     * */
    @Named("mapToPostCategoryDTO")
    @Mapping(source = "id", target = "postCategoryId")
    PostCategoryDTO mapToPostCategoryDTO(PostCategory postCategory);

    @Named("mapToPostCategory")
    @Mapping(source = "postCategoryId", target = "id")
    PostCategory mapToPostCategory(PostCategoryDTO postCategoryDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "slug", ignore = true)
    void updateFromPostCategoryDTO(PostCategoryDTO postCategoryDTO, @MappingTarget PostCategory postCategory);
}
