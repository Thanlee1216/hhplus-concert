package com.hhplus.concert.presentation.controller;

import com.hhplus.concert.presentation.dto.request.TokenRequestDTO;
import com.hhplus.concert.presentation.dto.response.TokenResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class TokenApiController {

    /**
     * Token 생성 API
     * @param requestDTO
     * @return
     */
    @PostMapping("/create")
    public TokenResponseDTO create(@RequestBody TokenRequestDTO requestDTO) {
        return new TokenResponseDTO("");
    }
}
