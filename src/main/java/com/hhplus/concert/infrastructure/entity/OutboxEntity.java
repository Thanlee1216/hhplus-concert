package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.OutboxStatusType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "outbox")
@Data
public class OutboxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outbox_seq")
    private Long outboxSeq;

    @Column(name = "topic")
    private String topic;

    @Column(name = "topic_key")
    private String topicKey;

    @Column(name = "payload")
    private String payload;

    @Column(name = "outbox_status")
    @Enumerated(EnumType.STRING)
    private OutboxStatusType outboxStatus;

    public OutboxEntity() {}
    public OutboxEntity(Long outboxSeq, String topic, String topicKey, String payload, OutboxStatusType outboxStatus) {
        this.outboxSeq = outboxSeq;
        this.topic = topic;
        this.topicKey = topicKey;
        this.payload = payload;
        this.outboxStatus = outboxStatus;
    }
}
