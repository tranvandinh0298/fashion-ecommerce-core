package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends SoftDeleting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name="order_number")
    private String orderNumber;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @NotBlank
    @Column(name="sub_total")
    private Double subTotal;

    @OneToOne
    @JoinColumn(name="shipping_id")
    private Shipping shipping;

    @NotNull
    @Column(name="coupon")
    private Double coupon;

    @NotNull
    @Column(name="total_amount")
    private Double totalAmount;

    @NotNull
    @Column(name="quantity")
    private Integer quantity;

    @NotBlank
    @Column(name="payment_method")
    private String paymentMethod;

    @NotBlank
    @Column(name="payment_status")
    private String paymentStatus;

    @NotBlank
    @Column(name="status")
    private String status;

    @NotBlank
    @Column(name="first_name")
    private String firstName;

    @NotBlank
    @Column(name="last_name")
    private String lastName;

    @NotBlank
    @Column(name="email")
    private String email;

    @NotBlank
    @Column(name="phone")
    private String phone;

    @NotBlank
    @Column(name="country")
    private String country;

    @NotBlank
    @Column(name="post_code")
    private String postCode;

    @NotBlank
    @Column(name="address1")
    private String address1;

    @NotBlank
    @Column(name="address2")
    private String address2;
}
