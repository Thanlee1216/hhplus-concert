package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.global.exception.JwtException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class QueueService {

    //한번에 처리 가능한 유입량
    //요구사항에 50좌석으로 제한되어 있기 때문에 적은 Limit로 설정
    private static final Long QUEUE_LIMIT = 5L;
    private static final Long ACTIVE_LIMIT_TIME = 600000L;

    private static final String ACTIVE_TOKEN_KEY = "activeToken";
    private static final String WAIT_TOKEN_KEY = "waitToken";

    @Autowired
    RedisTemplate redisTemplate;

    public QueueDomain queueEntry(QueueDomain queueDomain) {
        if(queueDomain.token() == null) {
            throw new JwtException("500", "Empty token");
        }

        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();

        Long freeQueueCount = QUEUE_LIMIT - setOperations.size(ACTIVE_TOKEN_KEY); //진입할 수 있는 남은 자리
        Long userQueueRank = zSetOperations.rank(WAIT_TOKEN_KEY, queueDomain.token()); //요청 유저의 순번

        //첫 요청이라면 대기열에 넣어준다.
        if(userQueueRank == null) {
            zSetOperations.add(WAIT_TOKEN_KEY, queueDomain.token(), System.currentTimeMillis());
            userQueueRank = zSetOperations.rank(WAIT_TOKEN_KEY, queueDomain.token());
        }

        userQueueRank++; //index + 1 -> index값으로 비교하는것이 아닌 대기 순번으로 비교하기 때문

        if(freeQueueCount == null || //Active된 사람이 없어서 첫 생성이면 바로 진입
                (freeQueueCount <= QUEUE_LIMIT && userQueueRank <= freeQueueCount) //진입할 수 있는 자리가 있고 and 나의 순서가 진입할 순서라면 진입
        ) {
            setOperations.add(ACTIVE_TOKEN_KEY, queueDomain.token() + ":" + System.currentTimeMillis());
            zSetOperations.remove(WAIT_TOKEN_KEY, queueDomain.token());

            return queueDomain.withUserQueueCount(0L); //기다릴 필요 없다고 return
        }

        return queueDomain.withUserQueueCount(userQueueRank); //userQueueRank만큼 기다려야 한다고 return
    }

    public boolean checkActiveToken(String token) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        Set<Object> activeTokenSet = setOperations.members(ACTIVE_TOKEN_KEY);
        return activeTokenSet.stream()
                .anyMatch(v -> String.valueOf(v).split("\\:")[0].equals(token));
    }

    public void expiredQueue() {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        Set<Object> activeTokenSet = setOperations.members(ACTIVE_TOKEN_KEY);
        activeTokenSet.forEach(e -> {
            Long activeTime = Long.valueOf(String.valueOf(e).split("\\:")[1]);
            if(System.currentTimeMillis() - activeTime > ACTIVE_LIMIT_TIME) {
                setOperations.remove(ACTIVE_TOKEN_KEY, e);
            }
        });
    }

}
