package com.source.dinhtv.fashionecommercecore.model;

import java.util.Date;

public class Image {
    private Integer id;
    private String caption;
    private String address;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Image(String caption, String address, Integer status) {
        this.caption = caption;
        this.address = address;
        this.status = status;
    }

    public Image(Integer id, String caption, String address, Integer status) {
        this.id = id;
        this.caption = caption;
        this.address = address;
        this.status = status;
    }

    public Image(Integer id, String caption, String address, Integer status, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.caption = caption;
        this.address = address;
        this.status = status;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getstatus() {
        return status;
    }

    public void setstatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", caption='" + caption + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                '}';
    }
}
