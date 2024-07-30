package com.hhplus.concert.application.facade;

import com.hhplus.concert.application.dto.QueueFacadeDTO;
import com.hhplus.concert.application.mapper.QueueFacadeMapper;
import com.hhplus.concert.business.constant.QueueStatusType;
import com.hhplus.concert.business.domain.QueueDomain;
import com.hhplus.concert.business.domain.UserDomain;
import com.hhplus.concert.business.service.QueueService;
import com.hhplus.concert.business.service.TokenService;
import com.hhplus.concert.business.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueFacade {

    @Autowired
    private QueueService queueService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserService userService;

    /**
     * 신규 유저 : Insert Queue
     * 대기 중이던 유저 : 현재 상태 확인 후 진입 가능하면 Update Queue, 진입 불가능하면 대기열 순번 Return
     * @param dto
     * @return
     */
    public QueueFacadeDTO queueEntry(QueueFacadeDTO dto) throws Exception {
        if(dto.token() == null) {
            UserDomain userDomain = userService.findUserById(dto.userId());
            dto = dto.withToken(tokenService.createToken(userDomain));
        }
        return QueueFacadeMapper.toQueueFacadeDTO(queueService.queueEntry(QueueFacadeMapper.toQueueDomain(dto)));
    }

    public boolean checkActiveToken(String token) {
        return queueService.checkActiveToken(token);
    }

    /**
     * 유저 대기열 만료
     */
    public void expiredQueue() {
        queueService.expiredQueue();
    }
}
