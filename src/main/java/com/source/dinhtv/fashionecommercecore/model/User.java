package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import com.source.dinhtv.fashionecommercecore.model.datetime.Timestamps;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import jakarta.

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User extends Timestamps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tên người dùng là bắt buộc")
    @Size(min=6, message="Tối thiểu 6 ký tự")
    @Column(name="name")
    private String name;

    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không hợp lệ")
    @Column(name="email")
    private String email;

    @Column(name="email_verified_at")
    private Date emailVerifiedAt;

    @NotNull(message = "Mật khẩu là bắt buộc")
    @Size(min=6, message = "Mật khẩu tối thiểu phải có 6 ký tự")
    @Column(name="password")
    private String password;

    @Column(name="remember_token")
    private String remember_token;
}
