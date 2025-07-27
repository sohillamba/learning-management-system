package com.lms.lms.services;

import com.lms.lms.entities.Lesson;

import java.util.List;

public interface LessonService {
    Lesson create(Long courseId, Lesson lesson);

    List<Lesson> getByCourseId(Long courseId);

    Lesson getById(Long id);

    Lesson update(Long id, Lesson lesson);

    void delete(Long id);
}
