package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name="transactions")
public class Transaction extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(name="code")
    private String code;
    @NotBlank
    @Column(name="amount")
    private Double amount;
    @NotBlank
    @Column(name="paymentFee")
    private Double paymentFee;
    @NotBlank
    @Column(name="totalAmount")
    private Double totalAmount;
    @NotBlank
    @Column(name="payment_method_id")
    private Integer payment_method_id;
    @NotBlank
    @Column(name="user_id")
    private Integer user_id;
    @NotBlank
    @Column(name="status")
    private Integer status;

    public Transaction() {

    }

    public Transaction(String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
        this.status = status;
    }

    public Transaction(Date createdAt, Date updatedAt, String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        super(createdAt, updatedAt);
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
        this.status = status;
    }

    public Transaction(Date createdAt, Date updatedAt, Date deletedAt, String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        super(createdAt, updatedAt, deletedAt);
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

    public Transaction(Date createdAt, Date updatedAt, Integer id, String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        super(createdAt, updatedAt);
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
        this.status = status;
    }

    public Transaction(Date createdAt, Date updatedAt, Date deletedAt, Integer id, String code, Double amount, Double paymentFee, Double totalAmount, Integer payment_method_id, Integer user_id, Integer status) {
        super(createdAt, updatedAt, deletedAt);
        this.id = id;
        this.code = code;
        this.amount = amount;
        this.paymentFee = paymentFee;
        this.totalAmount = totalAmount;
        this.payment_method_id = payment_method_id;
        this.user_id = user_id;
        this.status = status;
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

}
