package com.hhplus.concert.business.repository;

import com.hhplus.concert.business.domain.QueueDomain;

import java.util.List;

public interface UserQueueRepository {

    List<QueueDomain> getActiveQueues(String key);

    void insertActiveQueue(String key, String value);

    Long getUserQueueCount(String key, String token);

    void insertWaitQueue(String key, String token, Long currentTime);

    void deleteWaitQueue(String key, String token);

    void deleteActiveQueue(String key, String token);
}
