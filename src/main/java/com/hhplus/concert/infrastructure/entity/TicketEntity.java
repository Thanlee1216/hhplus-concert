package com.hhplus.concert.infrastructure.entity;

import com.hhplus.concert.business.constant.TicketStatusType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket")
@Data
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticket_Id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "concert_id")
    private Long concertId;

    @Column(name = "concert_name")
    private String concertName;

    @Column(name = "concert_option_id")
    private Long concertOptionId;

    @Column(name = "concert_option_name")
    private String concertOptionName;

    @Column(name = "concert_date")
    private Long concertDate;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "seat_num")
    private String seatNum;

    @Column(name = "ticket_status")
    private TicketStatusType status;

    public TicketEntity() {}
    public TicketEntity(Long ticketId, Long userId, Long reservationId, Long concertId, String concertName, Long concertOptionId, Long concertDate, Long seatId, String seatNum, TicketStatusType status) {
        this.ticket_Id = ticketId;
        this.userId = userId;
        this.reservationId = reservationId;
        this.concertId = concertId;
        this.concertName = concertName;
        this.concertOptionId = concertOptionId;
        this.concertOptionName = concertOptionName;
        this.concertDate = concertDate;
        this.seatId = seatId;
        this.seatNum = seatNum;
        this.status = status;
    }

}
