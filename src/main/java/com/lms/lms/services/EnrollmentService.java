package com.lms.lms.services;

import com.lms.lms.entities.enrollment.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment enroll(Long userId, Long courseId);

    List<Enrollment> listByUser(Long userId);

    List<Enrollment> listByCourse(Long courseId);

    void unEnroll(Long userId, Long courseId);
}
