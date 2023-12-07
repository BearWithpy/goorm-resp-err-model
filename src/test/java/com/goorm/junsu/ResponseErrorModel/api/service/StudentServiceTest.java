package com.goorm.junsu.ResponseErrorModel.api.service;


import com.goorm.junsu.ResponseErrorModel.api.domain.Student;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.StudentRequest;
import com.goorm.junsu.ResponseErrorModel.api.repository.StudentRepository;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class StudentServiceTest {

    @Mock
    private final StudentRepository repository;
    @InjectMocks
    private final StudentService studentService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Autowired
    public StudentServiceTest(StudentRepository repository, StudentService studentService) {
        this.repository = repository;
        this.studentService = studentService;
    }

    @Test
    @DisplayName("전부 가지고 오기")
    void getAll() {
        // given
        String name = "junsu";
        String grade = "5";
        StudentRequest studentRequest = StudentRequest.builder()
                .grade(grade)
                .name(name)
                .build();
        studentService.create(studentRequest);

        String name2 = "weee";
        String grade2 = "1";
        StudentRequest studentRequest2 = StudentRequest.builder()
                .grade(grade2)
                .name(name2)
                .build();
        studentService.create(studentRequest2);

        // when
        List<Student> result = studentService.getAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo(name2);
        assertThat(result.get(1).getName()).isEqualTo(name);

    }

    @Test
    @DisplayName("학생 생성")
    void create() {
        // given
        String name = "junsu";
        String grade = "3";
        StudentRequest studentRequest = StudentRequest.builder()
                .grade(grade)
                .name(name)
                .build();

        // when
        Student test = studentService.create(studentRequest);

        // then
        assertThat(test.getName()).isEqualTo(name);
        assertThat(test.getGrade()).isEqualTo(Integer.valueOf(grade));
    }

    @Test
    @DisplayName("특정 성적 가지고 오기")
    void getByGrade() {
        // given
        String name = "junsu";
        String grade = "5";
        StudentRequest studentRequest = StudentRequest.builder()
                .grade(grade)
                .name(name)
                .build();
        studentService.create(studentRequest);

        String name2 = "weee";
        String grade2 = "1";
        StudentRequest studentRequest2 = StudentRequest.builder()
                .grade(grade2)
                .name(name2)
                .build();
        studentService.create(studentRequest2);

        String name3 = "good";
        String grade3 = "5";
        StudentRequest studentRequest3 = StudentRequest.builder()
                .grade(grade3)
                .name(name3)
                .build();
        studentService.create(studentRequest3);

        // when
        List<Student> result = studentService.getStudentsByGrade(5);

        // then
        assertThat(result).hasSize(2);
    }
}