package com.cts.ResultApp.repo;

import com.cts.ResultApp.model.Course;
import com.cts.ResultApp.model.Result;
import com.cts.ResultApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudent(User student);
    // Check if a specific student already has a grade for a specific course
    boolean existsByStudentAndCourse(User student, Course course);
    // Finds the result record using the Student and Course objects
    Optional<Result> findByStudentAndCourse(User student, Course course);
}
