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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="categories")
public class Category extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name="name")
    private String name;
    @NotBlank
    @Column(name="slug")
    private String slug;
    @NotBlank
    @Column(name="description")
    private String description;
    @NotNull
    @Column(name="status")
    private Integer status;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
