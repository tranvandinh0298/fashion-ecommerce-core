package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name="title")
    private String title;

    @NotBlank
    @Column(name="slug")
    private String slug;

    @NotBlank
    @Column(name="summary")
    private String summary;

    @NotBlank
    @Column(name="description")
    private String description;

    @NotBlank
    @Column(name="quote")
    private String quote;

    @NotBlank
    @Column(name="photo")
    private String photo;

    @NotBlank
    @Column(name="tags")
    private String tags;

    @OneToOne
    @JoinColumn(name="post_cat_id")
    private PostCategory postCategory;

    @OneToOne
    @JoinColumn(name="post_tag_id")
    private PostTag postTag;

    @OneToOne
    @JoinColumn(name="added_by")
    private User user;

    @NotNull
    @Column(name="status")
    private String status;
}
