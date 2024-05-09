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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product extends SoftDeleting {
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
    @Column(name="photo")
    private String photo;

    @NotNull
    @Column(name="stock")
    private Integer stock;

    @NotBlank
    @Column(name="size")
    private String size;

    @NotBlank
    @Column(name="`condition`")
    private String condition;

    @NotNull
    @Column(name="status")
    private String status;

    @NotNull
    @Column(name="price")
    private Double price;

    @NotNull
    @Column(name="discount")
    private Double discount;

    @NotNull
    @Column(name="is_featured")
    private Integer isFeatured;

    @OneToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @OneToOne
    @JoinColumn(name = "child_cat_id")
    private Category childCategory;

    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
