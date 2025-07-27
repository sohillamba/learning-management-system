package com.lms.lms.services;

import com.lms.lms.entities.Choice;

import java.util.List;

public interface ChoiceService {

    Choice create(Long questionId, Choice choice);

    Choice getById(Long id);

    List<Choice> getByQuestionId(Long questionId);

    Choice update(Long id, Choice choice);

    void delete(Long id);
}
