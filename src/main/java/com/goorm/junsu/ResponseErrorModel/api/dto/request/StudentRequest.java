package com.goorm.junsu.ResponseErrorModel.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {
    private String name;
    private String grade;
}
