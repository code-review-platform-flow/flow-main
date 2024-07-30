package com.flow.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(name = "use_yn", nullable = false)
    private boolean useYn;

    @Column(name = "create_code", updatable = false)
    private String createCode;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedBy
    @Column(name = "modify_code")
    private String modifyCode;

    @LastModifiedDate
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @Column(name = "delete_code")
    private String deleteCode;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @PrePersist
    public void prePersist(){
        this.createDate = LocalDateTime.now();
        this.createCode = "flow-main";
        this.useYn = true;
    }

    public void markDeleted(String delete_code){
        this.deleteDate = LocalDateTime.now();
        this.deleteCode = delete_code;
        this.useYn = false;
    }
}
