package com.hhplus.concert.infrastructure.persistence.dataaccess.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueueRedisRepository {

    @Autowired
    RedisTemplate redisTemplate;

    public SetOperations<String, Object> getActiveQueues() {
        return redisTemplate.opsForSet();
    }

    public Long getUserQueueCount(String key, String token) {
        return redisTemplate.opsForZSet().rank(key, token);
    }

    public void insertActiveQueue(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void insertWaitQueue(String key, String token, Long currentTime) {
        redisTemplate.opsForZSet().add(key, token, currentTime);
    }

    public void deleteWaitQueue(String key, String token) {
        redisTemplate.opsForZSet().remove(key, token);
    }

    public void deleteActiveQueue(String key, String token) {
        redisTemplate.opsForSet().remove(key, token);
    }
}
