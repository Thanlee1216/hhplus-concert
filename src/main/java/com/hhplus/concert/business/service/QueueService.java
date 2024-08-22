package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.business.repository.UserQueueRepository;
import com.hhplus.concert.global.exception.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueService {

    //한번에 처리 가능한 유입량
    //요구사항에 50좌석으로 제한되어 있기 때문에 적은 Limit로 설정
    private static final Long QUEUE_LIMIT = 5L;
    private static final Long ACTIVE_LIMIT_TIME = 30000L;

    private static final String ACTIVE_TOKEN_KEY = "activeToken";
    private static final String WAIT_TOKEN_KEY = "waitToken";

    @Autowired
    UserQueueRepository queueRepository;

    public QueueDomain queueEntry(QueueDomain queueDomain) {
        if(queueDomain.token() == null) {
            throw new JwtException("500", "Empty token");
        }

        List<QueueDomain> activeQueueList = queueRepository.getActiveQueues(ACTIVE_TOKEN_KEY);
        Long userQueueRank = queueRepository.getUserQueueCount(WAIT_TOKEN_KEY, queueDomain.token()); //요청 유저의 순번

        Long freeQueueCount = QUEUE_LIMIT - activeQueueList.size(); //진입할 수 있는 남은 자리

        //첫 요청이라면 대기열에 넣어준다.
        if(userQueueRank == null) {
            queueRepository.insertWaitQueue(WAIT_TOKEN_KEY, queueDomain.token(), System.currentTimeMillis());
            userQueueRank = queueRepository.getUserQueueCount(WAIT_TOKEN_KEY, queueDomain.token());
        }

        userQueueRank++; //index + 1 -> index값으로 비교하는것이 아닌 대기 순번으로 비교하기 때문

        if(freeQueueCount == null || //Active된 사람이 없어서 첫 생성이면 바로 진입
                (freeQueueCount <= QUEUE_LIMIT && userQueueRank <= freeQueueCount) //진입할 수 있는 자리가 있고 and 나의 순서가 진입할 순서라면 진입
        ) {
            queueRepository.insertActiveQueue(ACTIVE_TOKEN_KEY, queueDomain.token() + ":" + System.currentTimeMillis());
            queueRepository.deleteWaitQueue(WAIT_TOKEN_KEY, queueDomain.token());

            return queueDomain.withUserQueueCount(0L); //기다릴 필요 없다고 return
        }

        return queueDomain.withUserQueueCount(userQueueRank); //userQueueRank만큼 기다려야 한다고 return
    }

    public boolean checkActiveToken(String token) {
        List<QueueDomain> activeQueueList = queueRepository.getActiveQueues(ACTIVE_TOKEN_KEY);
        return activeQueueList.stream()
                .anyMatch(v -> v.token().split("\\:")[0].equals(token));
    }

    public void expiredQueue() {
        List<QueueDomain> activeQueueList = queueRepository.getActiveQueues(ACTIVE_TOKEN_KEY);
        activeQueueList.forEach(v -> {
            Long activeTime = Long.valueOf(v.token().split("\\:")[1]);
            if(System.currentTimeMillis() - activeTime > ACTIVE_LIMIT_TIME) {
                queueRepository.deleteActiveQueue(ACTIVE_TOKEN_KEY, v.token());
            }
        });
    }



//    @Autowired
//    UserQueueRepository queueRepository;
//
//    public QueueDomain queueEntry(QueueDomain queueDomain) {
//        //Queue Status가 없으면 신규 유입
//        if(queueDomain.status() == null) {
//            queueDomain = queueRepository.insert(queueDomain.withStatus(QueueStatusType.WAIT).withCreatedAt(new Timestamp(System.currentTimeMillis())));
//        }
//        long runCount = queueRepository.countByStatus(QueueStatusType.RUN); //현재 활성화 된 유저 수
//        long userQueueCount = queueRepository.countByQueueNumberLessThanEqualAndStatusEquals(queueDomain.queueNumber(), queueDomain.status()); //요청자의 대기열 순번
//        if(runCount < QUEUE_LIMIT && userQueueCount <= QUEUE_LIMIT - runCount) { //활성화가 가능하고 내 차례라면 활성화
//            return queueRepository.update(queueDomain.withStatus(QueueStatusType.RUN).withActiveAt(new Timestamp(System.currentTimeMillis())));
//        }else {
//            return queueDomain.withUserQueueCount(userQueueCount);
//        }
//    }
//
//    @Transactional
//    public void expiredQueue() {
//        queueRepository.expiredQueue(QueueStatusType.DONE);
//    }

}
