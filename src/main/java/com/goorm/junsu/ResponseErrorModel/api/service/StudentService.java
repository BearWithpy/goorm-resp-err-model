package com.goorm.junsu.ResponseErrorModel.api.service;

import com.goorm.junsu.ResponseErrorModel.api.domain.Student;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.GradeRequest;
import com.goorm.junsu.ResponseErrorModel.api.dto.request.StudentRequest;
import com.goorm.junsu.ResponseErrorModel.api.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        List<Student> students = repository.findAll(Sort.by(Sort.Direction.ASC, "grade"));
        log.info("Found {} students", students.size());
        return students;
    }

    public Student create(StudentRequest newStudent) {
        Student student = Student.builder()
                .name(newStudent.getName())
                .grade(Integer.valueOf(newStudent.getGrade()))
                .build();
        log.info("Create Student: " + student);
        return repository.save(student);
    }

    public List<Student> getStudentsByGrade(GradeRequest targetGrade) {
        GradeRequest target = GradeRequest.builder()
                .grade(targetGrade.getGrade())
                .build();

        List<Student> byGrade = repository.findByGrade(target.getGrade());
        log.info("target -> {} Found {} students", targetGrade, byGrade.size());
        return byGrade;
    }
}
