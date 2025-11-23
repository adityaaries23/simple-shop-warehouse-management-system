package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@ToString
@Entity(name = "_rev_info")
@RevisionEntity(AuditRevisionEntity.Listener.class)
public class AuditRevisionEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    private int id;

    @RevisionTimestamp
    private long timestamp;

    private String user;

    @Transient
    public Date getRevisionDate() {
        return new Date(timestamp);
    }


    public static class Listener implements RevisionListener {
        @Override
        public void newRevision(Object revisionEntity) {
            AuditRevisionEntity auditedRevisionEntity = (AuditRevisionEntity) revisionEntity;
            auditedRevisionEntity.setUser("system");
        }
    }
}
