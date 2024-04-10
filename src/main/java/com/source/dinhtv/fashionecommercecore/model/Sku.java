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
@Table(name="skus")
public class Sku extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name="code")
    private String code;
    @NotNull
    @Column(name="product_id")
    private Integer productId;
    @NotNull
    @Column(name="quantity")
    private Integer quantity;
    @NotBlank
    @Column(name="price")
    private Double price;

//    public Sku() {
//    }
//
//    public Sku(String code, Integer productId, Integer quantity, Double price) {
//        this.code = code;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public Sku(Date createdAt, Date updatedAt, String code, Integer productId, Integer quantity, Double price) {
//        super(createdAt, updatedAt);
//        this.code = code;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public Sku(Date createdAt, Date updatedAt, Date deletedAt, String code, Integer productId, Integer quantity, Double price) {
//        super(createdAt, updatedAt, deletedAt);
//        this.code = code;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public Sku(Integer id, String code, Integer productId, Integer quantity, Double price) {
//        this.id = id;
//        this.code = code;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public Sku(Date createdAt, Date updatedAt, Integer id, String code, Integer productId, Integer quantity, Double price) {
//        super(createdAt, updatedAt);
//        this.id = id;
//        this.code = code;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public Sku(Date createdAt, Date updatedAt, Date deletedAt, Integer id, String code, Integer productId, Integer quantity, Double price) {
//        super(createdAt, updatedAt, deletedAt);
//        this.id = id;
//        this.code = code;
//        this.productId = productId;
//        this.quantity = quantity;
//        this.price = price;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public Integer getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Integer productId) {
//        this.productId = productId;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
}
