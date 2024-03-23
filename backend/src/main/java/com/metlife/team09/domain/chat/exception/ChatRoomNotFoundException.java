package com.metlife.team09.domain.chat.exception;

import com.metlife.team09.global.exception.BaseResponseStatus;
import com.metlife.team09.global.exception.BusinessException;

public class ChatRoomNotFoundException extends BusinessException {

    public ChatRoomNotFoundException() {
        super(BaseResponseStatus.CHAT_ROOM_NOT_FOUND);
    }
}
