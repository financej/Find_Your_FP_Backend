package com.metlife.team09.domain.auth.web;

import com.metlife.team09.domain.auth.application.dto.*;
import com.metlife.team09.domain.auth.application.AuthService;
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
}
