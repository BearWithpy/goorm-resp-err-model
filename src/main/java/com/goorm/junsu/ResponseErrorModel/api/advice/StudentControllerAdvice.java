package com.goorm.junsu.ResponseErrorModel.api.advice;


import com.goorm.junsu.ResponseErrorModel.api.dto.response.ApiResponse;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Status;
import com.goorm.junsu.ResponseErrorModel.api.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "com.goorm.junsu.ResponseErrorModel.api.controller")
public class StudentControllerAdvice {

    @ExceptionHandler(value = {CustomException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse<?> customExceptionHandler(final CustomException customException) {
        ApiResponse<CustomException> response = new ApiResponse<>();

        Status status = new Status();
        status.setMessage(customException.getMessage());
        status.setCode(customException.getErrorCode());

        Map<String, String> cause = new HashMap<>();
        if (customException.getData() != null && customException.getData().containsKey("invalidGrade")) {
            cause.put("invalidGrade", customException.getData().get("invalidGrade"));
        }
        if (customException.getData() != null && customException.getData().containsKey("invalidName")) {
            cause.put("invalidName", customException.getData().get("invalidName"));
        }
        Map<String, Map<String, String>> data = new HashMap<>();
        data.put("inputRestriction", cause);

        response.setStatus(status);
        response.setData(data);

        return response;
    }
}
