package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.constant.ReservationStatusType;
import com.hhplus.concert.business.domain.ReservationDomain;
import com.hhplus.concert.business.repository.ReservationRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.ReservationJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.ReservationEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;

    @Override
    public ReservationDomain save(ReservationDomain reservationDomain) {
        return ReservationEntityMapper.toReservationDomain(jpaRepository.save(ReservationEntityMapper.toReservationEntity(reservationDomain)));
    }

    @Override
    public ReservationDomain findByUserIdAndSeatIdAndStatus(Long userId, Long seatId, ReservationStatusType status) {
        return ReservationEntityMapper.toReservationDomain(jpaRepository.findByUserIdAndSeatIdAndStatus(userId, seatId, status));
    }

    @Override
    public ReservationDomain findById(Long reservationId) {
        return ReservationEntityMapper.toReservationDomain(jpaRepository.findById(reservationId));
    }

}
