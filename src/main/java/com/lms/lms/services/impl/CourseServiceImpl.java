package com.lms.lms.services.impl;

import com.lms.lms.entities.Course;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.CourseRepository;
import com.lms.lms.services.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepo;

    public CourseServiceImpl (CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public Course create(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course getById(Long id) {
        if (id == null) throw new IllegalArgumentException("Course ID must not be null!");
        return courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found for id " + id));
    }

    @Override
    public Course getWithContent(Long courseId) {
        if (courseId == null) throw new IllegalArgumentException("Course ID must not be null");
        return courseRepo.findByIdWithContent(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
    }

    @Override
    public Course update(Long id, Course course) {
        if (id == null) throw new IllegalArgumentException("Course ID must not be null!");
        Course ex = getById(id);
        ex.setTitle(course.getTitle());
        ex.setDescription(course.getDescription());
        ex.setInstructorName(course.getInstructorName());
        ex.setPrice(course.getPrice());
        return courseRepo.save(ex);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Course ID must not be null!");
        courseRepo.deleteById(id);
    }
}
