package com.source.dinhtv.fashionecommercecore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

//import jakarta.

import java.util.Date;

@Entity(name="users")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min=6, message="Tối thiểu 6 ký tự")
    private String name;

    @NotNull
    @Email
    private String email;

    @Null
    private Date emailVerifiedAt;

    @NotNull
    @Size(min=6, message = "Mật khẩu tối thiểu phải có 6 ký tự")
    private String password;

    @Null
    private String remember_token;

    private Date createdAt;

    private Date updatedAt;

    public User() {

    }

    public User(String name, String email, Date emailVerifiedAt, String password, String remember_token) {
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
        this.remember_token = remember_token;
    }

    public User(Integer id, String name, String email, Date emailVerifiedAt, String password, String remember_token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
        this.remember_token = remember_token;
    }
    public User(Integer id, String name, String email, Date emailVerifiedAt, String password, String remember_token, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
        this.remember_token = remember_token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Date emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
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
}
