package com.metlife.team09.domain.auth.application.dto;

public record TokenResponseDto(String accessToken,String userName,boolean isAdmin) {
}
