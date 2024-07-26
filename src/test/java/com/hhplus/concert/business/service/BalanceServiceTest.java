package com.hhplus.concert.business.service;

import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

    @InjectMocks
    BalanceService balanceService;

    @Mock
    UserRepository userRepository;

    @DisplayName("유저 ID로 잔액을 조회하는 테스트")
    @Test
    void findBalanceById() {
        //given
        long userId = 1L;
        UserDomain userDomain = new UserDomain(userId, "customer1", "Customer One", 1000L);
        when(userRepository.findById(userId)).thenReturn(userDomain);

        //when
        UserDomain result = balanceService.findBalanceById(userId);

        //then
        assertThat(result.userId()).isEqualTo(userId);
        assertThat(result.balance()).isEqualTo(1000L);
    }

    @DisplayName("존재하지 않는 유저 ID로 잔액을 조회하면 null을 반환하는 테스트")
    @Test
    void returnNullForNonExistentUserId() {
        //given
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(null);

        //when
        UserDomain result = balanceService.findBalanceById(userId);

        //then
        assertThat(result).isNull();
    }

    @DisplayName("유저의 잔액을 업데이트하는 테스트")
    @Test
    void updateBalance() {
        //given
        Long userId = 1L;
        Long initialBalance = 2000L;
        Long amountToAdd = 1000L;
        Long expectedBalance = initialBalance + amountToAdd;

        UserDomain userDomain = new UserDomain(userId, "customer1", "Customer One", initialBalance);
        when(userRepository.findById(userId)).thenReturn(userDomain);
        when(userRepository.save(any(UserDomain.class))).thenAnswer(invocation -> invocation.getArgument(0));

        //when
        UserDomain result = balanceService.updateBalance(userId, amountToAdd);

        //then
        assertThat(result.userId()).isEqualTo(userId);
        assertThat(result.balance()).isEqualTo(expectedBalance);
    }

}