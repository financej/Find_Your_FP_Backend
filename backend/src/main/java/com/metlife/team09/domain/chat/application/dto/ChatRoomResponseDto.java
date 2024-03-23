package com.metlife.team09.domain.chat.application.dto;

import com.metlife.team09.domain.chat.persistence.Chat;

public record ChatRoomResponseDto(
        Long roomId,
        Long customerId,
        Long plannerId
) {
    public static ChatRoomResponseDto from(final Chat chat) {
        Long customerId = null;
        if(chat.getCustomer() != null) {
            customerId = chat.getCustomer().getId();
        }

        Long plannerId = null;
        if(chat.getPlanner() != null) {
            plannerId = chat.getPlanner().getId();
        }

        return new ChatRoomResponseDto(chat.getId(), customerId, plannerId);
    }
}
