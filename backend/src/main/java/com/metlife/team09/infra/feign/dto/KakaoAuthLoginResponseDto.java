package com.metlife.team09.infra.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoAuthLoginResponseDto(@JsonProperty("access_token") String accessToken) {
}
