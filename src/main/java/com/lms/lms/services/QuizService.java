package com.lms.lms.services;

import com.lms.lms.entities.Quiz;

import java.util.List;

public interface QuizService {

    Quiz create(Long courseId, Quiz quiz);

    Quiz getById(Long id);

    List<Quiz> getByCourseId(Long courseId);

    Quiz update(Long id, Quiz quiz);

    void delete(Long id);
}
