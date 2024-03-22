package com.metlife.team09.domain.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public record KakaoAuthInfoResponseDto(
	String id,
	@JsonProperty("kakao_account") KakaoAccount kakaoAccount) {
	@Getter
	public static class KakaoAccount {
		private String email;
	}
}
