package com.source.dinhtv.fashionecommercecore.model;

import java.util.Date;

public class Transaction {
    private Integer id;
    private String code;
    private Double amount;
    private Double paymentFee;
    private Double totalAmount;
    private Integer payment_method_id;
    private Integer user_id;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Transaction(String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
        this.status = status;
    }

    public Transaction(Integer id, String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
        this.status = status;
    }

    public Transaction(Integer id, String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status, Date createdAt, Date updatedAt, Date deletedAt) {
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(Double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(Integer payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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
}
