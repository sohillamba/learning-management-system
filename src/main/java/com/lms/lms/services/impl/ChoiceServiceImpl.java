package com.lms.lms.services.impl;

import com.lms.lms.entities.Choice;
import com.lms.lms.entities.Question;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.ChoiceRepository;
import com.lms.lms.repo.QuestionRepository;
import com.lms.lms.services.ChoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository choiceRepo;
    private final QuestionRepository questionRepo;

    public ChoiceServiceImpl(ChoiceRepository choiceRepo, QuestionRepository questionRepo) {
        this.choiceRepo = choiceRepo;
        this.questionRepo = questionRepo;
    }

    @Override
    public Choice create(Long questionId, Choice choice) {
        if (questionId == null) throw new IllegalArgumentException("Question ID must not be null");
        Question question = questionRepo.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
        choice.setQuestion(question);
        return choiceRepo.save(choice);
    }

    @Override public Choice getById(Long id) {
        if (id == null) throw new IllegalArgumentException("Choice ID must not be null");
        return choiceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Choice not found with id " + id));
    }

    @Override public List<Choice> getByQuestionId(Long questionId) {
        if (questionId == null) throw new IllegalArgumentException("Question ID must not be null");
        return choiceRepo.findByQuestionId(questionId);
    }

    @Override public Choice update(Long id, Choice choice) {
        if (id == null) throw new IllegalArgumentException("Choice ID must not be null for update");
        Choice existing = getById(id);
        existing.setText(choice.getText());
        existing.setCorrect(choice.isCorrect());
        return choiceRepo.save(existing);
    }

    @Override public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Choice ID must not be null for delete");
        choiceRepo.deleteById(id);
    }
}
