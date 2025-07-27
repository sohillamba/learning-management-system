package com.lms.lms.services;

import com.lms.lms.entities.Course;

import java.util.List;

public interface CourseService {

    Course create(Course course);

    List<Course> getAll();

    Course getById(Long id);

    Course getWithContent(Long courseId);

    Course update(Long id, Course course);

    void delete(Long id);
}
