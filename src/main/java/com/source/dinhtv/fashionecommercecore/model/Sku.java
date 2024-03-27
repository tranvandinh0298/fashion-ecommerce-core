package com.source.dinhtv.fashionecommercecore.model;

import java.util.Date;

public class Sku {
    private Integer id;
    private String code;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Sku(String code, Integer productId, Integer quantity, Double price) {
        this.code = code;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Sku(Integer id, String code, Integer productId, Integer quantity, Double price) {
        this.id = id;
        this.code = code;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Sku(Integer id, String code, Integer productId, Integer quantity, Double price, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.code = code;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
