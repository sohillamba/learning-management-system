package com.lms.lms.services;

import com.lms.lms.entities.lessonProgress.LessonProgress;

import java.util.List;

public interface LessonProgressService {

    LessonProgress markCompleted(Long userId, Long lessonId);

    LessonProgress markIncomplete(Long userId, Long lessonId);

    List<LessonProgress> getProgressByUser(Long userId);

    List<LessonProgress> getProgressByCourse(Long userId, Long courseId);

    double getCourseProgressPercent(Long userId, Long courseId);
}
