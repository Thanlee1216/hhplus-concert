package com.hhplus.concert.infrastructure.persistence.implement;

import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.business.repository.UserQueueRepository;
import com.hhplus.concert.infrastructure.persistence.dataaccess.redis.UserQueueRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserQueueRepositoryImpl implements UserQueueRepository {

    private final UserQueueRedisRepository repository;


    @Override
    public List<QueueDomain> getActiveQueues(String key) {
        List<QueueDomain> queues = new ArrayList<>();
        repository.getActiveQueues().members(key).forEach(queue -> {
            queues.add(new QueueDomain(null, null, queue.toString()));
        });
        return queues;
    }

    @Override
    public void insertActiveQueue(String key, String value) {
        repository.insertActiveQueue(key, value);
    }

    @Override
    public Long getUserQueueCount(String key, String token) {
        return repository.getUserQueueCount(key, token);
    }

    @Override
    public void insertWaitQueue(String key, String token, Long currentTime) {
        repository.insertWaitQueue(key, token, currentTime);
    }

    @Override
    public void deleteWaitQueue(String key, String token) {
        repository.deleteWaitQueue(key, token);
    }

    @Override
    public void deleteActiveQueue(String key, String token) {
        repository.deleteActiveQueue(key, token);
    }
}
