package com.lms.lms.services.impl;

import com.lms.lms.entities.Course;
import com.lms.lms.entities.Lesson;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.CourseRepository;
import com.lms.lms.repo.LessonRepository;
import com.lms.lms.services.LessonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepo;
    private final CourseRepository courseRepo;

    public LessonServiceImpl(LessonRepository lessonRepo, CourseRepository courseRepo) {
        this.lessonRepo = lessonRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public Lesson create(Long courseId, Lesson lesson) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
        lesson.setCourse(course);
        return lessonRepo.save(lesson);
    }

    @Override
    public Lesson getById(Long id) {
        if (id == null) throw new IllegalArgumentException("Lesson ID must not be null");
        return lessonRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id " + id));
    }

    @Override
    public List<Lesson> getByCourseId(Long courseId) {
        if (courseId == null) throw new IllegalArgumentException("Course ID must not be null");
        return lessonRepo.findByCourseId(courseId);
    }

    @Override
    public Lesson update(Long id, Lesson lesson) {
        if (id == null) throw new IllegalArgumentException("Lesson ID must not be null for update");
        Lesson existing = getById(id);
        existing.setTitle(lesson.getTitle());
        existing.setVideoUrl(lesson.getVideoUrl());
        existing.setResourceLinks(lesson.getResourceLinks());
        return lessonRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Lesson ID must not be null for delete");
        lessonRepo.deleteById(id);
    }
}
