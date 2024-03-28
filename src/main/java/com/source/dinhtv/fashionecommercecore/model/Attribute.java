package com.source.dinhtv.fashionecommercecore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity(name="attributes")
public class Attribute {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @Size(min=1,message = "Tên thuộc tính phải lớn 1 ký tự")
    private String name;

    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Attribute() {
    }

    public Attribute(String name) {
        this.name = name;
    }

    public Attribute(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Attribute(Integer id, String name, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.name = name;
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
