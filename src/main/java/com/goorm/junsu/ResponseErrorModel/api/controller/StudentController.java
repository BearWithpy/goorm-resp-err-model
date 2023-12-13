package com.goorm.junsu.ResponseErrorModel.api.controller;


import com.goorm.junsu.ResponseErrorModel.api.domain.Student;

import com.goorm.junsu.ResponseErrorModel.api.dto.request.GradeRequest;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.StudentRequest;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.ApiResponse;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Metadata;
import com.goorm.junsu.ResponseErrorModel.api.dto.response.Status;
import com.goorm.junsu.ResponseErrorModel.api.exception.CustomException;
import com.goorm.junsu.ResponseErrorModel.api.exception.ErrorCode;
import com.goorm.junsu.ResponseErrorModel.api.service.StudentService;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/user")
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final Metadata metadata;
    private final Status status;

    private Map<String, String> causeData;

    @Autowired
    public StudentController(StudentService studentService, Metadata metadata, Status status) {
        this.studentService = studentService;
        this.metadata = metadata;
        this.status = status;
    }

    @PostMapping("/create")
    @ResponseBody
    public ApiResponse<Student> createStudent(@RequestBody @Validated StudentRequest studentRequest, BindingResult results) throws CustomException {
        log.info(studentRequest.getGrade());
        log.info(studentRequest.getName());


        if (results.hasFieldErrors("name")) {
            causeData = Map.of("invalidName", "Name Not Found");
            throw new CustomException(ErrorCode.NAME_NOT_FOUND.getCode(), ErrorCode.NAME_NOT_FOUND.getErrorMessage(), causeData);
        }
        if (results.hasFieldErrors("grade")) {
            log.error("grade is {}", results.getFieldValue("grade"));

            if (StringUtils.isBlank(studentRequest.getGrade())) {
                causeData = Map.of("invalidGrade", "Grade Not Found");
                throw new CustomException(ErrorCode.GRADE_NOT_FOUND.getCode(), ErrorCode.GRADE_NOT_FOUND.getErrorMessage(), causeData);
            }

            causeData = Map.of("invalidGrade", studentRequest.getGrade());
            throw new CustomException(ErrorCode.INVALID_GRADE.getCode(), ErrorCode.INVALID_GRADE.getErrorMessage(), causeData);
        }

        Student createdStudent = studentService.create(studentRequest);
        return makeResponse(createdStudent);
    }


    @GetMapping("/check")
    public ApiResponse<Student> searchAllStudent() {
        List<Student> result = studentService.getAll();
        if(result.size() == 0){
            causeData = Map.of("noStudent", "Student Not Found");
            throw new CustomException(ErrorCode.EMPTY_STUDENT.getCode(), ErrorCode.EMPTY_STUDENT.getErrorMessage());
        }
        return makeResponse(result);

    }

    @PostMapping("/grade")
    @ResponseBody
    public ApiResponse<Student> searchTargetGradeStudent(@RequestBody GradeRequest gradeRequest) {
        log.info(String.valueOf(gradeRequest.getGrade()));
        List<Student> result = studentService.getStudentsByGrade(gradeRequest);
        if(result.size() == 0){
            throw new CustomException(ErrorCode.EMPTY_STUDENT.getCode(), ErrorCode.EMPTY_STUDENT.getErrorMessage());
        }
        return makeResponse(result);
    }


    private <T> ApiResponse<T> makeResponse(T result) {
        ApiResponse<T> response = new ApiResponse<>();
        List<T> results = new ArrayList<>();
        results.add(result);

        status.setCode(2001);
        status.setMessage("Created!");
        response.setStatus(status);

        metadata.setResultCount(1);
        response.setMetadata(metadata);

        response.setResults(results);
        return response;
    }

    private <T> ApiResponse<T> makeResponse(List<T> results) {
        ApiResponse<T> response = new ApiResponse<>();

        status.setCode(2000);
        status.setMessage("OK");
        response.setStatus(status);

        metadata.setResultCount(results.size());
        response.setMetadata(metadata);

        response.setResults(results);
        return response;
    }
}
