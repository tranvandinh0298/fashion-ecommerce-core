package com.source.dinhtv.fashionecommercecore.model;

import com.source.dinhtv.fashionecommercecore.model.datetime.SoftDeleting;
import com.source.dinhtv.fashionecommercecore.model.datetime.Timestamps;
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
public class User extends Timestamps {
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

    public User() {

    }

    public User(String name, String email, Date emailVerifiedAt, String password, String remember_token) {
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
        this.remember_token = remember_token;
    }

    public User(Date createdAt, Date updatedAt, String name, String email, Date emailVerifiedAt, String password, String remember_token) {
        super(createdAt, updatedAt);
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

    public User(Date createdAt, Date updatedAt, Integer id, String name, String email, Date emailVerifiedAt, String password, String remember_token) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerifiedAt = emailVerifiedAt;
        this.password = password;
        this.remember_token = remember_token;
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
}
