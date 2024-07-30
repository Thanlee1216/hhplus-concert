package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.constant.QueueStatusType;
import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.business.repository.UserQueueRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.UserQueueJpaRepository;
import com.hhplus.concert.infrastructure.persistence.mapper.QueueEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserQueueRepositoryImpl implements UserQueueRepository {

    private final UserQueueJpaRepository jpaRepository;

//    @Override
//    public QueueDomain findByQueueNumber(Long queueNumber) {
//        return QueueEntityMapper.toQueueDomain(jpaRepository.findById(queueNumber));
//    }
//
//    @Override
//    public QueueDomain insert(QueueDomain queueDomain) {
//        return QueueEntityMapper.toQueueDomain(jpaRepository.save(QueueEntityMapper.toQueueEntity(queueDomain)));
//    }
//
//    @Override
//    public Long countByStatus(QueueStatusType status) {
//        return jpaRepository.countByStatus(status);
//    }
//
//    @Override
//    public QueueDomain update(QueueDomain queueDomain) {
//        return QueueEntityMapper.toQueueDomain(jpaRepository.save(QueueEntityMapper.toQueueEntity(queueDomain)));
//    }
//
//    @Override
//    public Long countByQueueNumberLessThanEqualAndStatusEquals(Long queueNumber, QueueStatusType status) {
//        return jpaRepository.countByQueueNumberLessThanEqualAndStatusEquals(queueNumber, status);
//    }
//
//    @Override
//    public Long expiredQueue(QueueStatusType status) {
//        return jpaRepository.expiredQueue(status).longValue();
//    }

}
