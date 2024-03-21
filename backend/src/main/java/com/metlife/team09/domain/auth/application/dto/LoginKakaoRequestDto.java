package com.metlife.team09.domain.auth.application.dto;

public record LoginKakaoRequestDto(String redirectUri, String code) {
}
// 카카오로그인 요청한 프론트 측 주소
