package com.source.dinhtv.fashionecommercecore.model.datetime;

import java.util.Date;

public class SoftDeleting extends Timestamps {
    private Date deletedAt;

    public SoftDeleting() {
        super();
    }

    public SoftDeleting(Date createdAt, Date updatedAt) {
        super(createdAt, updatedAt);
    }

    public SoftDeleting(Date createdAt, Date updatedAt, Date deletedAt) {
        super(createdAt, updatedAt);
        this.deletedAt = deletedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
