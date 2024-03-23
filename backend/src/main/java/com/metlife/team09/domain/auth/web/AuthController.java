package com.metlife.team09.domain.auth.web;

import com.metlife.team09.domain.auth.application.dto.*;
import com.metlife.team09.domain.auth.application.AuthService;
import com.metlife.team09.infra.feign.dto.KakaoAuthLoginResponseDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/kakao")
    public ResponseEntity<TokenResponseDto> loginKakao(@RequestBody final LoginKakaoRequestDto request) {
        final TokenResponseDto response = authService.loginKakao(request);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/accessToken")
    public ResponseEntity<?> getKaKaoAccessToken(@RequestBody final LoginKakaoRequestDto request ){
        KakaoAuthLoginResponseDto kakaoLoginAccessToken = authService.getKakaoLoginAccessToken(request);

        return ResponseEntity.ok(KakaoTokenResponseDto.builder().accessToken(kakaoLoginAccessToken.accessToken()));
    }
}
