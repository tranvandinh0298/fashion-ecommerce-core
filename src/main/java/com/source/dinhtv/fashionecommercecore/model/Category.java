package com.source.dinhtv.fashionecommercecore.model;

import java.util.Date;

public class Category {
    private Integer id;
    private String name;
    private String slug;
    private String description;
    private Integer status;
    private Integer imageId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Category(String name, String slug, String description, Integer status, Integer imageId) {
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

    public Category(Integer id, String name, String slug, String description, Integer status, Integer imageId, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.status = status;
        this.imageId = imageId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
