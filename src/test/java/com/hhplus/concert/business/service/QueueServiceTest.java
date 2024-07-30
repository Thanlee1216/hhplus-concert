package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.global.exception.JwtException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class QueueServiceTest {

    private static final String ACTIVE_TOKEN_KEY = "activeToken";
    private static final String WAIT_TOKEN_KEY = "waitToken";

    @Autowired
    private QueueService queueService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @AfterEach
    void tearDown() {
        // 테스트 후 Redis 데이터를 정리합니다.
        redisTemplate.delete(ACTIVE_TOKEN_KEY);
        redisTemplate.delete(WAIT_TOKEN_KEY);
    }

    @DisplayName("대기열 생성 테스트")
    @Test
    void queueEntry_NewEntry() {
        //given
        QueueDomain queueDomain = new QueueDomain(1L, null, "token");

        //when
        QueueDomain result = queueService.queueEntry(queueDomain);

        //then
        assertThat(result).isNotNull();
        assertThat(result.userQueueCount()).isEqualTo(0L);
    }

    @DisplayName("토큰이 없는 경우 예외 발생 테스트")
    @Test
    void queueEntry_NoToken() {
        //given
        QueueDomain queueDomain = new QueueDomain(1L, null, null);

        //when
        JwtException exception = assertThrows(JwtException.class, () -> queueService.queueEntry(queueDomain));

        //then
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).isEqualTo("Empty token");
    }

    @DisplayName("대기열을 활성화할 수 없는 경우 테스트")
    @Test
    void queueEntry_NotActivated() {
        QueueDomain queueDomain = new QueueDomain(1L, null, "token");
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        setOperations.add(ACTIVE_TOKEN_KEY, "token1:" + System.currentTimeMillis());
        setOperations.add(ACTIVE_TOKEN_KEY, "token2:" + System.currentTimeMillis());
        setOperations.add(ACTIVE_TOKEN_KEY, "token3:" + System.currentTimeMillis());
        setOperations.add(ACTIVE_TOKEN_KEY, "token4:" + System.currentTimeMillis());
        setOperations.add(ACTIVE_TOKEN_KEY, "token5:" + System.currentTimeMillis());

        zSetOperations.add(WAIT_TOKEN_KEY, "token", System.currentTimeMillis());
        zSetOperations.add(WAIT_TOKEN_KEY, "token6", System.currentTimeMillis() - 1);

        QueueDomain result = queueService.queueEntry(queueDomain);

        assertThat(result).isNotNull();
        assertThat(result.userQueueCount()).isEqualTo(1L);
        assertThat(setOperations.isMember(ACTIVE_TOKEN_KEY, "token")).isFalse();
        assertThat(zSetOperations.rank(WAIT_TOKEN_KEY, "token")).isNotNull();
    }

    @DisplayName("만료된 큐를 처리하는 테스트")
    @Test
    void expiredQueue() {
        //given
        Long currentTime = System.currentTimeMillis();
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        setOperations.add(ACTIVE_TOKEN_KEY, "token1:" + (currentTime - 700000L));
        setOperations.add(ACTIVE_TOKEN_KEY, "token2:" + currentTime);

        //when
        queueService.expiredQueue();

        //then
        assertThat(setOperations.isMember(ACTIVE_TOKEN_KEY, "token1:" + (currentTime - 700000L))).isFalse();
        assertThat(setOperations.isMember(ACTIVE_TOKEN_KEY, "token2:" + currentTime)).isTrue();
    }
}
