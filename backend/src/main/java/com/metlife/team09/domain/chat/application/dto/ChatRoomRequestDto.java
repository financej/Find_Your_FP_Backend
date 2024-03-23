package com.metlife.team09.domain.chat.application.dto;

public record ChatRoomRequestDto(
        Long roomId,
        Long userId
) {
}