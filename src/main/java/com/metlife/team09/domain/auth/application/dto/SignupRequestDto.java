package com.metlife.team09.domain.auth.application.dto;

public record SignupRequestDto(
        String email,
        String password
) {
}
