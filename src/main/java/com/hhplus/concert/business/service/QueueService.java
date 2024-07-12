package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.QueueStatusType;
import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.business.repository.UserQueueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class QueueService {

    //한번에 처리 가능한 유입량
    //요구사항에 50좌석으로 제한되어 있기 때문에 적은 Limit로 설정
    private static final Long QUEUE_LIMIT = 5L;

    @Autowired
    UserQueueRepository queueRepository;

    public QueueDomain queueEntry(QueueDomain queueDomain) {
        //Queue Status가 없으면 신규 유입
        if(queueDomain.status() == null) {
            queueDomain = queueRepository.insert(queueDomain.withStatus(QueueStatusType.WAIT).withCreatedAt(new Timestamp(System.currentTimeMillis())));
        }
        long runCount = queueRepository.countByStatus(QueueStatusType.RUN); //현재 활성화 된 유저 수
        long userQueueCount = queueRepository.countByQueueNumberLessThanEqualAndStatusEquals(queueDomain.queueNumber(), queueDomain.status()); //요청자의 대기열 순번
        if(runCount < QUEUE_LIMIT && userQueueCount <= QUEUE_LIMIT - runCount) { //활성화가 가능하고 내 차례라면 활성화
            return queueRepository.update(queueDomain.withStatus(QueueStatusType.RUN).withActiveAt(new Timestamp(System.currentTimeMillis())));
        }else {
            return queueDomain.withUserQueueCount(userQueueCount);
        }
    }

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void expiredQueue() {
        queueRepository.expiredQueue(QueueStatusType.DONE);
    }
}
