package com.metlife.team09.global.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonPropertyOrder({"message", "result"})    // json 요청 순서 정의
public class BaseResponse<T> {
    private final String message;
    private final T result;

    @JsonIgnore
    private final HttpStatus httpStatus;

    private BaseResponse(
            final BaseResponseStatus status,
            final T result
    ) {
        this.message = status.getMessage();
        this.httpStatus = status.getHttpStatus();
        this.result = result;
    }

    public static <T> BaseResponse<T> of (
            final BaseResponseStatus status,
            final T result
    ) {
        return new BaseResponse<>(
                status,
                result
        );
    }
}
