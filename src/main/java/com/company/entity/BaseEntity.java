package com.company.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_time")
    private Date updatedTime;

    @PrePersist
    protected void onCreate(){
        if(createTime == null){
            updatedTime = createTime = new Date();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        if (updatedTime == null) {
            updatedTime = new Date();
        }
    }
}
