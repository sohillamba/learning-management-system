package com.lms.lms.services.impl;

import com.lms.lms.entities.Course;
import com.lms.lms.entities.User;
import com.lms.lms.entities.enrollment.Enrollment;
import com.lms.lms.entities.enrollment.EnrollmentId;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.CourseRepository;
import com.lms.lms.repo.EnrollmentRepository;
import com.lms.lms.repo.UserRepository;
import com.lms.lms.services.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepo;
    private final UserRepository userRepo;
    private final CourseRepository courseRepo;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepo,
                                 UserRepository userRepo,
                                 CourseRepository courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public Enrollment enroll(Long userId, Long courseId) {
        if (userId == null || courseId == null) throw new IllegalArgumentException("User id or course id is null!");
        EnrollmentId id = new EnrollmentId(userId, courseId);
        if (enrollmentRepo.existsById(id)) throw new IllegalArgumentException("Already enrolled");

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found: " + courseId));
        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        return enrollmentRepo.save(enrollment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollment> listByUser(Long userId) {
        if (userId == null) throw new IllegalArgumentException("User id is null!");
        return enrollmentRepo.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enrollment> listByCourse(Long courseId) {
        if (courseId == null) throw new IllegalArgumentException("Course id is null!");
        return enrollmentRepo.findByCourseId(courseId);
    }

    @Override
    public void unEnroll(Long userId, Long courseId) {
        if (userId == null || courseId == null) throw new IllegalArgumentException("User id or course id is null!");
        EnrollmentId id = new EnrollmentId(userId, courseId);
        if (!enrollmentRepo.existsById(id)) throw new ResourceNotFoundException("Enrollment not found");
        enrollmentRepo.deleteById(id);
    }
}
