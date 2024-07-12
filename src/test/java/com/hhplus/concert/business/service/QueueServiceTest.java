package com.hhplus.concert.business.service;

import com.hhplus.concert.business.constant.QueueStatusType;
import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.business.repository.UserQueueRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueueServiceTest {

    @InjectMocks
    QueueService queueService;

    @Mock
    UserQueueRepository queueRepository;

    @DisplayName("대기열 생성 테스트")
    @Test
    void queueEntry_NewEntry() {
        //given
        QueueDomain queueDomain = new QueueDomain(1L, 1L, null, null, null, null);
        QueueDomain insertedQueueDomain = queueDomain.withStatus(QueueStatusType.WAIT).withCreatedAt(new Timestamp(System.currentTimeMillis()));

        when(queueRepository.insert(any(QueueDomain.class))).thenReturn(insertedQueueDomain);
        when(queueRepository.countByStatus(QueueStatusType.RUN)).thenReturn(0L);
        when(queueRepository.countByQueueNumberLessThanEqualAndStatusEquals(anyLong(), any(QueueStatusType.class))).thenReturn(1L);

        QueueDomain updatedQueueDomain = insertedQueueDomain.withStatus(QueueStatusType.RUN).withActiveAt(new Timestamp(System.currentTimeMillis()));
        when(queueRepository.update(any(QueueDomain.class))).thenReturn(updatedQueueDomain);

        //when
        QueueDomain result = queueService.queueEntry(queueDomain);

        //then
        assertThat(result).isNotNull();
        assertThat(result.status()).isEqualTo(QueueStatusType.RUN);
        verify(queueRepository, times(1)).insert(any(QueueDomain.class));
        verify(queueRepository, times(1)).update(any(QueueDomain.class));
    }

    @DisplayName("대기열 업데이트 테스트")
    @Test
    void queueEntry_UpdateEntry() {
        //given
        QueueDomain queueDomain = new QueueDomain(1L, 1L, null, null, QueueStatusType.WAIT, 1L);

        when(queueRepository.countByStatus(QueueStatusType.RUN)).thenReturn(1L);
        when(queueRepository.countByQueueNumberLessThanEqualAndStatusEquals(anyLong(), any(QueueStatusType.class))).thenReturn(1L);
        when(queueRepository.update(any(QueueDomain.class))).thenReturn(queueDomain.withStatus(QueueStatusType.RUN).withActiveAt(new Timestamp(System.currentTimeMillis())));

        //when
        QueueDomain result = queueService.queueEntry(queueDomain);

        //then
        assertThat(result).isNotNull();
        assertThat(result.status()).isEqualTo(QueueStatusType.RUN);
        verify(queueRepository, times(1)).update(any(QueueDomain.class));
    }

    @DisplayName("대기열을 활성화할 수 없는 경우 테스트")
    @Test
    void queueEntry_NotActivated() {
        //given
        QueueDomain queueDomain = new QueueDomain(1L, 1L, null, null, QueueStatusType.WAIT, 1L);

        when(queueRepository.countByStatus(QueueStatusType.RUN)).thenReturn(5L);
        when(queueRepository.countByQueueNumberLessThanEqualAndStatusEquals(anyLong(), any(QueueStatusType.class))).thenReturn(6L);

        //when
        QueueDomain result = queueService.queueEntry(queueDomain);

        //then
        assertThat(result).isNotNull();
        assertThat(result.status()).isEqualTo(QueueStatusType.WAIT);
        verify(queueRepository, never()).update(any(QueueDomain.class));
    }

    @DisplayName("만료된 큐를 처리하는 테스트")
    @Test
    void expiredQueue() {
        //when
        queueService.expiredQueue();

        //then
        verify(queueRepository, times(1)).expiredQueue(QueueStatusType.DONE);
    }
}