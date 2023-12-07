package com.goorm.junsu.ResponseErrorModel.api.controller;


import com.goorm.junsu.ResponseErrorModel.api.domain.Student;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.StudentRequest;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.ApiResponse;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Metadata;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Status;
import com.goorm.junsu.ResponseErrorModel.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class StudentController {

    private final StudentService studentService;
    //    private final ApiResponse<Student> apiResponse;
    private final Metadata metadata;
    private final Status status;

    @Autowired
    public StudentController(StudentService studentService, Metadata metadata, Status status) {
        this.studentService = studentService;
//        this.apiResponse = apiResponse;
        this.metadata = metadata;
        this.status = status;
    }

    @PostMapping("/create")
    @ResponseBody
    public ApiResponse<Student> createStudent(@RequestBody StudentRequest studentRequest) {
        Student createdStudent = studentService.create(studentRequest);
        return makeResponse(createdStudent);
    }

    @GetMapping("/check")
    @ResponseBody
    public ApiResponse<Student> searchAllStudent() {
        List<Student> result = studentService.getAll();
        return makeResponse(result);
    }


    private <T> ApiResponse<T> makeResponse(T result) {
        ApiResponse<T> response = new ApiResponse<>();
        List<T> results = new ArrayList<>();
        results.add(result);

        status.setCode(200);
        status.setMessage("OK");

        response.setResults(results);

        metadata.setResultCount(1);
        response.setMetadata(metadata);

        return response;
    }

    private <T> ApiResponse<T> makeResponse(List<T> results) {
        ApiResponse<T> response = new ApiResponse<>();

        status.setCode(200);
        status.setMessage("OK");

        response.setResults(results);

        metadata.setResultCount(results.size());
        response.setMetadata(metadata);

        return response;
    }
}
