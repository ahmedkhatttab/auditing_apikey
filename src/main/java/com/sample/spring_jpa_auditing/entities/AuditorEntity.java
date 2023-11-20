package com.sample.spring_jpa_auditing.entities;

import com.sample.spring_jpa_auditing.securities.SignedUserHelper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Slf4j
public abstract class AuditorEntity {

    @CreatedDate
    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @CreatedBy
    @Column(name = "CreatedBy", length = 50)
    private String createdBy;


    @LastModifiedDate
    @Column(name = "UpdatedOn")
    private LocalDateTime updatedOn;

    @LastModifiedBy
    @Column(name = "UpdatedBy", length = 50)
    private String updatedBy;

    @Column(name = "DeletedOn")
    private LocalDateTime deletedOn;

    @Column(name = "DeletedBy", length = 50)
    private String deletedBy;

    @Column(name = "isDeleted", length = 50)
    private Boolean isDeleted = false;

    @Transient
    private LocalDateTime tempCreatedOn;

    @Transient
    private LocalDateTime tempUpdatedOn;

    @PreUpdate
    @PrePersist
    public void beforeAnyUpdate() {
        log.info("###########################");
        log.info("{}:{}, {}:{}", createdBy, createdOn, updatedBy, updatedOn);
//        if("".equals(createdBy) || createdBy==null){
//            log.info("created by is empty");
//            createdBy="ahmed khatab";
//        }
        if(tempCreatedOn!=null){
            createdOn = tempCreatedOn;
            updatedOn = tempUpdatedOn;
            createdBy="ahmed khatab";
            updatedBy="ahmed khatab";
        }
//        if(tempUpdatedOn!=null){
//            updatedOn = tempUpdatedOn;
//        }

        if (isDeleted != null && isDeleted) {

            if (deletedBy == null) {
                deletedBy = SignedUserHelper.userId().toString();
            }

            if (getDeletedOn() == null) {
                deletedOn = LocalDateTime.now();
            }
        }
    }

}
