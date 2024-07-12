package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.QueueStatusType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "user_queue")
@Data
public class UserQueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queue_number")
    private Long queueNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private QueueStatusType status;

    public UserQueueEntity() {}
    public UserQueueEntity(Long queueNumber, Long userId, Timestamp createdAt, Timestamp modifiedAt, QueueStatusType status) {
        this.queueNumber = queueNumber;
        this.userId = userId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.status = status;
    }

}
