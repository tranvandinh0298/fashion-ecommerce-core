package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity(name="categories")
public class Category extends SoftDeleting {
    @Id
    @GeneratedValue
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
    @NotBlank
    @Column(name="status")
    private Integer status;
    @NotBlank
    @Column(name="image_id")
    private Integer imageId;

    public Category() {
    }

    public Category(String name, String slug, String description, Integer status, Integer imageId) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
    }

    public Category(Date createdAt, Date updatedAt, String name, String slug, String description, Integer status, Integer imageId) {
        super(createdAt, updatedAt);
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
    }

    public Category(Date createdAt, Date updatedAt, Date deletedAt, String name, String slug, String description, Integer status, Integer imageId) {
        super(createdAt, updatedAt, deletedAt);
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
    }

    public Category(Integer id, String name, String slug, String description, Integer status, Integer imageId) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
    }

    public Category(Date createdAt, Date updatedAt, Integer id, String name, String slug, String description, Integer status, Integer imageId) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
    }

    public Category(Date createdAt, Date updatedAt, Date deletedAt, Integer id, String name, String slug, String description, Integer status, Integer imageId) {
        super(createdAt, updatedAt, deletedAt);
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
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

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
