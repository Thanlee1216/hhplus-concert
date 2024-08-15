package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.application.event.BalanceSuccessEvent;
import com.hhplus.concert.business.constant.BalanceTransactionType;
import com.hhplus.concert.business.domain.BalanceHistoryDomain;
import com.hhplus.concert.business.repository.BalanceHistoryRepository;
import com.hhplus.concert.business.repository.UserRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"balance-success"}, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class BalanceFacadeIntegrationTest {

    @Autowired
    private BalanceFacade balanceFacade;

    @Autowired
    private BalanceHistoryRepository balanceHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    private CountDownLatch latch;

    @BeforeEach
    public void setup() {
        latch = new CountDownLatch(1);
    }

    @KafkaListener(topics = "balance-success", groupId = "test-group")
    public void listen(ConsumerRecord<String, BalanceSuccessEvent> record) {
        latch.countDown();
    }

    @Test
    public void testChargeBalance() throws Exception {
        // Given
        BalanceFacadeDTO balanceFacadeDTO = new BalanceFacadeDTO(1L, 100L);

        // When
        balanceFacade.chargeBalance(balanceFacadeDTO);

        // Then
        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);
        assertThat(messageConsumed).isTrue();

        BalanceHistoryDomain historyDomain = balanceHistoryRepository.findFirstByUserIdOrderByTransactionDateDesc(1L);
        assertThat(historyDomain).isNotNull();
        assertThat(historyDomain.userId()).isEqualTo(1L);
        assertThat(historyDomain.amount()).isEqualTo(100L);
        assertThat(historyDomain.transactionType()).isEqualTo(BalanceTransactionType.CHARGE);
    }
}
