package com.metlife.team09.domain.chat.application.dto;

import com.metlife.team09.domain.chat.persistence.Chat;

public record ChatRoomResponseDto(
        Long roomId,
        Long customerId,
        Long plannerId
) {
    public static ChatRoomResponseDto from(final Chat chat) {
        return new ChatRoomResponseDto(chat.getId(), chat.getCustomer().getId(), chat.getPlanner().getId());
    }
}
