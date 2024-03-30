package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity(name="images")
public class Image extends SoftDeleting {
    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    @Column(name="caption")
    private String caption;
    @NotBlank
    @Column(name="address")
    private String address;
    @NotBlank
    @Column(name="status")
    private Integer status;

    public Image() {
    }

    public Image(String caption, String address, Integer status) {
        this.caption = caption;
        this.address = address;
        this.status = status;
    }

    public Image(Date createdAt, Date updatedAt, String caption, String address, Integer status) {
        super(createdAt, updatedAt);
        this.caption = caption;
        this.address = address;
        this.status = status;
    }

    public Image(Date createdAt, Date updatedAt, Date deletedAt, String caption, String address, Integer status) {
        super(createdAt, updatedAt, deletedAt);
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

    public Image(Date createdAt, Date updatedAt, Integer id, String caption, String address, Integer status) {
        super(createdAt, updatedAt);
        this.id = id;
        this.caption = caption;
        this.address = address;
        this.status = status;
    }

    public Image(Date createdAt, Date updatedAt, Date deletedAt, Integer id, String caption, String address, Integer status) {
        super(createdAt, updatedAt, deletedAt);
        this.id = id;
        this.caption = caption;
        this.address = address;
        this.status = status;
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
}
