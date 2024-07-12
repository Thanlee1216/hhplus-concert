package com.hhplus.concert.business;

import com.hhplus.concert.application.dto.BalanceFacadeDTO;
import com.hhplus.concert.application.facade.BalanceFacade;
import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BalanceFacadeIntegrationTest {

    @Autowired
    BalanceFacade balanceFacade;

    @Autowired
    UserRepository userRepository;

    @DisplayName("유저 ID로 잔액을 조회하는 통합 테스트")
    @Test
    void findBalanceById() {
        //given
        UserDomain userDomain = new UserDomain(1L, "1", "이태한", 1000L);
        userRepository.save(userDomain);

        //when
        BalanceFacadeDTO result = balanceFacade.findBalanceById(1L);

        //then
        assertThat(result).isNotNull();
        assertThat(result.userId()).isEqualTo(1L);
        assertThat(result.amount()).isEqualTo(1000L);
    }

    @DisplayName("포인트 충전 통합 테스트")
    @Test
    void chargeBalance() {
        //given
        UserDomain userDomain = new UserDomain(1L, "1", "이태한", 1000L);
        userRepository.save(userDomain);

        BalanceFacadeDTO balanceFacadeDTO = new BalanceFacadeDTO(1L, 500L);

        //when
        BalanceFacadeDTO result = balanceFacade.chargeBalance(balanceFacadeDTO);

        //then
        assertThat(result).isNotNull();
        assertThat(result.userId()).isEqualTo(1L);
        assertThat(result.amount()).isEqualTo(1500L); // 1000L + 500L
    }
}
