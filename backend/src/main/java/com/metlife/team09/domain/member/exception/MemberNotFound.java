package com.metlife.team09.domain.member.exception;

import com.metlife.team09.global.exception.BaseResponseStatus;
import com.metlife.team09.global.exception.BusinessException;

public class MemberNotFound extends BusinessException {

    public MemberNotFound() {
        super(BaseResponseStatus.MEMBER_NOT_FOUND);
    }
}
