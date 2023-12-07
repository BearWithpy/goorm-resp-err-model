package com.goorm.junsu.ResponseErrorModel.api.controller;


import com.goorm.junsu.ResponseErrorModel.api.domain.Student;

import com.goorm.junsu.ResponseErrorModel.api.dto.request.GradeRequest;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.StudentRequest;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.ApiResponse;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Metadata;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Status;
import com.goorm.junsu.ResponseErrorModel.api.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@Slf4j
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

    @PostMapping( "/create")
    @ResponseBody
    public ApiResponse<Student> createStudent(@RequestBody StudentRequest studentRequest) {
        log.info(studentRequest.getGrade());
        log.info(studentRequest.getName());
        Student createdStudent = studentService.create(studentRequest);
        return makeResponse(createdStudent);
    }

    @GetMapping("/check")
    public ApiResponse<Student> searchAllStudent() {
        try{
            List<Student> result = studentService.getAll();
            return makeResponse(result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new NoSuchElementException(e);
        }

    }

    @PostMapping("/grade")
    @ResponseBody
    public ApiResponse<Student> searchTargetGradeStudent(@RequestBody GradeRequest gradeRequest) {
        log.info(String.valueOf(gradeRequest.getGrade()));
        List<Student> result = studentService.getStudentsByGrade(gradeRequest);
        return makeResponse(result);
    }


    private <T> ApiResponse<T> makeResponse(T result) {
        ApiResponse<T> response = new ApiResponse<>();
        List<T> results = new ArrayList<>();
        results.add(result);

        status.setCode(201);
        status.setMessage("Created!");
        response.setStatus(status);
        metadata.setResultCount(1);
        response.setMetadata(metadata);
        response.setResults(results);

        return response;
    }

    private <T> ApiResponse<T> makeResponse(List<T> results) {
        ApiResponse<T> response = new ApiResponse<>();

        status.setCode(200);
        status.setMessage("OK");
        response.setStatus(status);

        metadata.setResultCount(results.size());
        response.setMetadata(metadata);

        response.setResults(results);
        return response;
    }
}
