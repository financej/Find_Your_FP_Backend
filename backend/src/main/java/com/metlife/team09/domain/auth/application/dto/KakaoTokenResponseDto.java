package com.metlife.team09.domain.auth.application.dto;

import lombok.Builder;

@Builder
public record KakaoTokenResponseDto(String accessToken) {
}
