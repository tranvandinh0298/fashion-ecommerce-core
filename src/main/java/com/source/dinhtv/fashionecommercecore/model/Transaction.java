package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

}
