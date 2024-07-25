package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class BalanceFacadeLockTest {


    @Autowired
    private BalanceFacade balanceFacade;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("포인트 충전 및 차감 동시성 테스트")
    @Test
    public void testConcurrentChargeAndDeductBalance() throws ExecutionException, InterruptedException {
        // Given
        Long userId = 1L;
        Long amountToCharge = 2000L;
        Long amountToDeduct = -1500L;

        BalanceFacadeDTO chargeDTO = new BalanceFacadeDTO(userId, amountToCharge);
        BalanceFacadeDTO deductDTO = new BalanceFacadeDTO(userId, amountToDeduct);

        // When
        CompletableFuture<BalanceFacadeDTO> chargeFuture = CompletableFuture.supplyAsync(() -> balanceFacade.chargeBalance(chargeDTO));
        CompletableFuture<BalanceFacadeDTO> deductFuture = CompletableFuture.supplyAsync(() -> balanceFacade.chargeBalance(deductDTO));

        CompletableFuture.allOf(chargeFuture, deductFuture).join();

        BalanceFacadeDTO chargeResult = chargeFuture.get();
        BalanceFacadeDTO deductResult = deductFuture.get();

        // Then
        assertThat(chargeResult.userId()).isEqualTo(userId);
        assertThat(deductResult.userId()).isEqualTo(userId);

        // Calculate the expected final balance
        Long finalExpectedBalance = amountToCharge + amountToDeduct;

        UserDomain finalUser = userRepository.findById(userId);
        assertThat(finalUser.balance()).isEqualTo(finalExpectedBalance);
    }

}