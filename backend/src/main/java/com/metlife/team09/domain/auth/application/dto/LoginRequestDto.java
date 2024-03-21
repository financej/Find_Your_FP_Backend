package com.metlife.team09.domain.auth.application.dto;

public record LoginRequestDto(
        String email,
        String password
) {
}
