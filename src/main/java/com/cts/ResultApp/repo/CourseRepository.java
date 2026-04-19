package com.cts.ResultApp.repo;

import com.cts.ResultApp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
