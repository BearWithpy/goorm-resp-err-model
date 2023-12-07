package com.goorm.junsu.ResponseErrorModel.api.dto.response;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ApiResponse<T> {

    private Status status;
    private Metadata metadata;
    private List<T> results;

    @Autowired
    public void setStatus(Status status) {
        this.status = status;
    }

    @Autowired
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}