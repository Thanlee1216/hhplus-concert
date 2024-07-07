package com.hhplus.concert.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "concert_hall")
public class ConcertHallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hall_id")
    private Long hallId;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name = "hall_address")
    private String hallAddress;
}
