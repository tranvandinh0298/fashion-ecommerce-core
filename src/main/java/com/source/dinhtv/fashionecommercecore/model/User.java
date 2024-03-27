package com.source.dinhtv.fashionecommercecore.model;

import java.util.Date;

public class User {
    private Integer id;
    private String name;
    private String email;
    private Date emailVerifiedAt;
    private String password;
    private String remember_token;
    private Date createdAt;
    private Date updatedAt;

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


}
