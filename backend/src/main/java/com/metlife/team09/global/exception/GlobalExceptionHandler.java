package com.metlife.team09.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<?>> handlerBusinessException(final BusinessException ex) {
        log.error("[Error] HttpStatus: {}, Message: {}", ex.getResponseStatus().getHttpStatus(), ex.getResponseStatus().getMessage());
        return buildResponse(ex.getResponseStatus());
    }

    private ResponseEntity<BaseResponse<?>> buildResponse(final BaseResponseStatus status) {
        final BaseResponse<?> baseResponse = BaseResponse.of(status, status.getHttpStatus());
        return new ResponseEntity<>(baseResponse, status.getHttpStatus());
    }

}
