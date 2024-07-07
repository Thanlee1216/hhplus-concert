package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.global.constant.TicketStatusType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "concert_ticket")
@Data
public class ConcertTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticket_Id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_name")
    private String concertName;

    @Column(name = "concert_date")
    private Timestamp concertDate;

    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "concert_option_name")
    private String concertOptionName;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_name")
    private String seatName;

    @Column(name = "ticket_status")
    private TicketStatusType status;

}
