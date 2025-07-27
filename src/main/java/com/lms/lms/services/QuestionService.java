package com.lms.lms.services;

import com.lms.lms.entities.Question;

import java.util.List;

public interface QuestionService {

    Question create(Long quizId, Question question);

    Question getById(Long id);

    List<Question> getByQuizId(Long quizId);

    Question update(Long id, Question question);

    void delete(Long id);
}
