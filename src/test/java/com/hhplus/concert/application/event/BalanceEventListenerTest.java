package com.hhplus.concert.application.event;

import com.hhplus.concert.business.constant.BalanceTransactionType;
import com.hhplus.concert.business.constant.OutboxStatusType;
import com.hhplus.concert.business.domain.OutboxDomain;
import com.hhplus.concert.business.repository.OutboxRepository;
import com.hhplus.concert.business.service.OutboxService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"balance-success"}, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class BalanceEventListenerTest {

    @Autowired
    private KafkaTemplate<String, BalanceSuccessEvent> kafkaTemplate;

    @Autowired
    private BalanceEventListener balanceEventListener;

    @Autowired
    private OutboxService outboxService;

    @Autowired
    private OutboxRepository outboxRepository;

    private CountDownLatch latch;

    @BeforeEach
    public void setup() {
        latch = new CountDownLatch(1);
    }

    @Test
    public void testInsertBalanceHistory() throws InterruptedException {
        // Given
        BalanceSuccessEvent event = new BalanceSuccessEvent(null, 1L, 100L, BalanceTransactionType.CHARGE, System.currentTimeMillis());
        kafkaTemplate.send("balance-success", "1", event);

        // When
        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);

        // Then
        assertThat(messageConsumed).isTrue();
    }

    @KafkaListener(topics = "balance-success", groupId = "test-group")
    public void listen(ConsumerRecord<String, BalanceSuccessEvent> record) {
        latch.countDown();
    }

    @Test
    public void testUpdateOutboxStatus() throws InterruptedException {
        // Given
        OutboxDomain outboxDomain = new OutboxDomain(null, "balance-success", "1", "{\"userId\":1,\"amount\":100,\"transactionType\":\"CHARGE\",\"transactionDate\":1234567890}", OutboxStatusType.INIT);
        outboxService.insertOutbox(outboxDomain);

        BalanceSuccessEvent event = new BalanceSuccessEvent(1L, 1L, 100L, BalanceTransactionType.CHARGE, System.currentTimeMillis());
        kafkaTemplate.send("balance-success", "1", event);

        // When
        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);

        // Then
        assertThat(messageConsumed).isTrue();
    }
}