package com.lms.lms.services.impl;

import com.lms.lms.entities.Question;
import com.lms.lms.entities.Quiz;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.QuestionRepository;
import com.lms.lms.repo.QuizRepository;
import com.lms.lms.services.QuestionService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepo;
    private final QuizRepository quizRepo;

    public QuestionServiceImpl(QuestionRepository questionRepo, QuizRepository quizRepo) {
        this.questionRepo = questionRepo;
        this.quizRepo = quizRepo;
    }

    @Override
    public Question create(Long quizId, Question question) {
        if (quizId == null) throw new IllegalArgumentException("Quiz ID must not be null");
        Quiz quiz = quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id " + quizId));
        question.setQuiz(quiz);
        return questionRepo.save(question);
    }

    @Override
    public Question getById(Long id) {
        if (id == null) throw new IllegalArgumentException("Question ID must not be null");
        return questionRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
    }

    @Override
    public List<Question> getByQuizId(Long quizId) {
        if (quizId == null) throw new IllegalArgumentException("Quiz ID must not be null");
        return questionRepo.findByQuizId(quizId);
    }

    @Override
    public Question update(Long id, Question question) {
        if (id == null) throw new IllegalArgumentException("Question ID must not be null for update");
        Question existing = getById(id);
        if (StringUtils.isNotEmpty(question.getText())) {
            existing.setText(question.getText());
        }
        if (question.getMarks() != null) {
            existing.setMarks(question.getMarks());
        }
        if (question.getChoices() != null && !question.getChoices().isEmpty()) {
            existing.setChoices(question.getChoices());
        }
        return questionRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Question ID must not be null for delete");
        questionRepo.deleteById(id);
    }
}
