package com.metlife.team09.domain.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAuthLoginResponseDto(@JsonProperty("access_token") String accessToken) {
}
