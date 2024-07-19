package com.hhplus.concert.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "balance")
    private Long balance;

    public UsersEntity() {}
    public UsersEntity(Long userId, String customerId, String customerName, Long balance) {
        this.userId = userId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.balance = balance;
    }
}
