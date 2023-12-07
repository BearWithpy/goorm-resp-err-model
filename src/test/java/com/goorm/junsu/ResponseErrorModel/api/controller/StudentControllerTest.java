package com.goorm.junsu.ResponseErrorModel.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goorm.junsu.ResponseErrorModel.api.domain.Student;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.StudentRequest;
import com.goorm.junsu.ResponseErrorModel.api.repository.StudentRepository;
import com.goorm.junsu.ResponseErrorModel.api.service.StudentService;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class StudentControllerTest {


    private MockMvc mockMvc;


    private ObjectMapper objectMapper;

    private final StudentService studentService;
    private final StudentRepository repository;

    @Autowired
    public StudentControllerTest(StudentService studentService, StudentRepository repository, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.repository = repository;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void createStudentTest() throws Exception {


    }


}