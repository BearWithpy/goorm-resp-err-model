package com.goorm.junsu.ResponseErrorModel.api.repository;

import com.goorm.junsu.ResponseErrorModel.api.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGrade(int targetGrade);
}