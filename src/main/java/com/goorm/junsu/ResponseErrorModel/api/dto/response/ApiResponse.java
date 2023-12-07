package com.goorm.junsu.ResponseErrorModel.api.dto.response;


import java.util.List;

@lombok.Data
public class ApiResponse<T> {
    private Status status;
    private Metadata metadata;
    private List<T> results;
    private List<T> data;
}
