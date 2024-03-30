package com.source.dinhtv.fashionecommercecore.model.datetime;

import java.util.Date;

public class Timestamps {
    private Date createdAt;
    private Date updatedAt;

    public Timestamps() {
    }

    public Timestamps(Date createdAt, Date updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
