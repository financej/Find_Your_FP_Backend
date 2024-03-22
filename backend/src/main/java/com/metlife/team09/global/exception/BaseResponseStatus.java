package com.metlife.team09.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter


    public enum BaseResponseStatus {
        //TODO REQUEST, RESPONSE
        LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공하였습니다."),
        SIGNUP_COMPLETE(HttpStatus.OK, "회원가입이 완료되었습니다."),


        //TODO Error
        LOGIN_FAILED(HttpStatus.BAD_REQUEST, "존재하지 않는 사용자입니다."),
        TOKEN_EXPIRE(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
        RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "리소스가 존재하지 않습니다."),
        ACCESS_DENIED(HttpStatus.UNAUTHORIZED, "권한이 없습니다.");


    private final HttpStatus httpStatus;
    private final String message;


    BaseResponseStatus(
            final HttpStatus httpStatus,
            final String message
    ) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}