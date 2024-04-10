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

    @NotBlank
    @Size(min=6, message="Tối thiểu 6 ký tự")
    @Column(name="name")
    private String name;

    @NotBlank
    @Email(message = "Email không hợp lệ")
    @Column(name="email")
    private String email;

    @Null
    @Column(name="email_verified_at")
    private Date emailVerifiedAt;

    @NotNull
    @Size(min=6, message = "Mật khẩu tối thiểu phải có 6 ký tự")
    @Column(name="password")
    private String password;

    @Null
    @Column(name="remember_token")
    private String remember_token;

//    public User() {
//
//    }
//
//    public User(String name, String email, Date emailVerifiedAt, String password, String remember_token) {
//        this.name = name;
//        this.email = email;
//        this.emailVerifiedAt = emailVerifiedAt;
//        this.password = password;
//        this.remember_token = remember_token;
//    }
//
//    public User(Date createdAt, Date updatedAt, String name, String email, Date emailVerifiedAt, String password, String remember_token) {
//        super(createdAt, updatedAt);
//        this.name = name;
//        this.email = email;
//        this.emailVerifiedAt = emailVerifiedAt;
//        this.password = password;
//        this.remember_token = remember_token;
//    }
//
//    public User(Integer id, String name, String email, Date emailVerifiedAt, String password, String remember_token) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.emailVerifiedAt = emailVerifiedAt;
//        this.password = password;
//        this.remember_token = remember_token;
//    }
//
//    public User(Date createdAt, Date updatedAt, Integer id, String name, String email, Date emailVerifiedAt, String password, String remember_token) {
//        super(createdAt, updatedAt);
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.emailVerifiedAt = emailVerifiedAt;
//        this.password = password;
//        this.remember_token = remember_token;
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Date getEmailVerifiedAt() {
//        return emailVerifiedAt;
//    }
//
//    public void setEmailVerifiedAt(Date emailVerifiedAt) {
//        this.emailVerifiedAt = emailVerifiedAt;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRemember_token() {
//        return remember_token;
//    }
//
//    public void setRemember_token(String remember_token) {
//        this.remember_token = remember_token;
//    }
}
