package com.lms.lms.controllers;

import com.lms.lms.entities.enrollment.Enrollment;
import com.lms.lms.services.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Enrollment enroll(@RequestParam Long userId, @RequestParam Long courseId) {
        return enrollmentService.enroll(userId, courseId);
    }

    @GetMapping("/user/{userId}")
    public List<Enrollment> listByUser(@PathVariable Long userId) {
        return enrollmentService.listByUser(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<Enrollment> listByCourse(@PathVariable Long courseId) {
        return enrollmentService.listByCourse(courseId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unEnroll(@RequestParam Long userId, @RequestParam Long courseId) {
        enrollmentService.unEnroll(userId, courseId);
    }
}
