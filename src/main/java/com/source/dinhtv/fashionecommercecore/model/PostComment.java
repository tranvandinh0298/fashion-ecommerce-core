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
@Table(name = "banners")
public class PostComment extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne
    @JoinColumn(name="post_id")
    private Post post;

    @NotBlank
    @Column(name="comment")
    private String comment;

    @NotNull
    @Column(name="status")
    private String status;

    @NotBlank
    @Column(name="replied_comment")
    private String repliedComment;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private PostComment parentComment;
}
