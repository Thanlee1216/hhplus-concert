package com.hhplus.concert.business;

import com.google.common.collect.Lists;
import com.hhplus.concert.infrastructure.entity.ConcertOptionEntity;
import com.hhplus.concert.infrastructure.persistence.dataaccess.jpa.ConcertOptionJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DataCreateTest {

    @Autowired
    ConcertOptionJpaRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(false)
    public void test() {
        List<ConcertOptionEntity> concertOptions = new ArrayList<>();
        for(int i = 450000; i < 500000; i++) {
            ConcertOptionEntity concertOption = new ConcertOptionEntity(2L, "season " + (i + 4), 1725768759000L + (i * 1000L * 60L * 60L * 24L), 1725768759000L, "N");
            concertOptions.add(concertOption);
        }
        Lists.partition(concertOptions, 500)
                .forEach(chunkedConcerts -> {
                    repository.saveAll(chunkedConcerts);
                    entityManager.flush();
                    entityManager.clear();
                });
    }
}
