package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name="products")
public class Product extends SoftDeleting {
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
    @Column(name="code")
    private String code;
    @NotBlank
    @Column(name="description")
    private String description;
    @NotBlank
    @Column(name="status")
    private Integer status;

    public Product() {
    }

    public Product(String name, String slug, String code, String description, Integer status) {
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Product(Date createdAt, Date updatedAt, String name, String slug, String code, String description, Integer status) {
        super(createdAt, updatedAt);
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Product(Date createdAt, Date updatedAt, Date deletedAt, String name, String slug, String code, String description, Integer status) {
        super(createdAt, updatedAt, deletedAt);
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Product(Integer id, String name, String slug, String code, String description, Integer status) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Product(Date createdAt, Date updatedAt, Integer id, String name, String slug, String code, String description, Integer status) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Product(Date createdAt, Date updatedAt, Date deletedAt, Integer id, String name, String slug, String code, String description, Integer status) {
        super(createdAt, updatedAt, deletedAt);
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.code = code;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getstatus() {
        return status;
    }

    public void setstatus(Integer status) {
        this.status = status;
    }
}
