package com.metlife.team09.domain.auth.application;

import com.metlife.team09.domain.auth.application.dto.LoginRequestDto;
import com.metlife.team09.domain.auth.application.dto.LoginResponseDto;
import com.metlife.team09.domain.auth.application.dto.SignupRequestDto;
import com.metlife.team09.domain.auth.application.dto.SignupResponseDto;
import com.metlife.team09.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@Valid @RequestBody final SignupRequestDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody final LoginRequestDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
}
