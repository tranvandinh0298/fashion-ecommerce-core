package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="categories")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends SoftDeleting {
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
    @Column(name="photo")
    private String photo;

    @NotNull
    @Column(name="is_parent")
    private Boolean isParent;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

    @OneToOne
    @JoinColumn(name="added_by")
    private User user;

    @NotNull
    @Column(name="status")
    private Integer status;
}
