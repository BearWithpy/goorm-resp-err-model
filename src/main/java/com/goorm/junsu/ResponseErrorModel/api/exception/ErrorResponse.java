package com.goorm.junsu.ResponseErrorModel.api.exception;

import lombok.Data;

@Data
public class ErrorResponse {

    private ErrorCode errorCode;
    private String message;
    private Object data;

    public ErrorResponse(ErrorCode errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }
}