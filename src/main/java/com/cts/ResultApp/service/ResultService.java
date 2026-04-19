package com.cts.ResultApp.service;

import com.cts.ResultApp.dto.*;
import com.cts.ResultApp.exception.custom.*;
import com.cts.ResultApp.model.*;
import com.cts.ResultApp.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Added for logging
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    /**
     * Registers a new user into the system.
     *
     * @param request the signup data.
     * @return the saved User entity.
     * @throws DuplicateResourceException if username is already taken.
     */
    @Transactional
    public User registerUser(SignupRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException(
                    getMsg("error.resource.duplicate", request.getUsername()));
        }

        if (userRepository.existsByEmailId(request.getEmailId())) {
            throw new DuplicateResourceException(
                    getMsg("error.resource.duplicate", request.getEmailId()));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmailId(request.getEmailId());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    /**
     * Retrieves all academic results for a specific student.
     *
     * @param username the username of the student.
     * @return a list of formatted result responses.
     */
    @Transactional(readOnly = true)
    public List<ResultResponse> getResultsByStudentUsername(String username) {
        User student = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(getMsg("error.resource.not_found", username)));
        return resultRepository.findByStudent(student).stream()
                .map(this::convertToResponse).collect(Collectors.toList());
    }

    /**
     * Handles both creation and update of student results.
     * If a record exists for the given Student and Course, it updates the marks.
     * Otherwise, it creates a new record.
     *
     * @param request the result details containing student ID, course ID, and marks.
     * @return the formatted result response.
     */
    @Transactional
    public ResultResponse createOrUpdateResult(ResultCreationRequest request) {
        // 1. Fetch Student and Course
        User student = userRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(getMsg("error.resource.not_found", "Student ID " + request.getStudentId())));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException(getMsg("error.resource.not_found", "Course ID " + request.getCourseId())));

        // 2. Locate existing result or prepare a new one
        Result result = resultRepository.findByStudentAndCourse(student, course)
                .map(existingResult -> {
                    log.info("Updating existing result record (ID: {}) for Student: {}", existingResult.getId(), student.getUsername());
                    return existingResult;
                })
                .orElseGet(() -> {
                    log.info("Creating new result record for Student: {} in Course: {}", student.getUsername(), course.getCourseName());
                    Result newResult = new Result();
                    newResult.setStudent(student);
                    newResult.setCourse(course);
                    return newResult;
                });

        // 3. Apply changes
        result.setMarksObtained(request.getMarksObtained());
        result.setGrade(calculateGrade(request.getMarksObtained()));

        // 4. Save and Convert
        return convertToResponse(resultRepository.save(result));
    }

    private String getMsg(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }

    private String calculateGrade(Integer marks) {
        if (marks == null) return "N/A";
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 50) return "C";
        return "F";
    }

    private ResultResponse convertToResponse(Result r) {
        return new ResultResponse(
                r.getStudent().getFirstName() + " " + r.getStudent().getLastName(),
                r.getCourse().getCourseName(),
                r.getCourse().getCourseCode(),
                r.getMarksObtained(),
                r.getGrade()
        );
    }
}
