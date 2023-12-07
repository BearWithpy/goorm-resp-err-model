package com.goorm.junsu.ResponseErrorModel.api.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GradeRequest {
    private int grade;
    private double cost;
}
