package com.lms.lms.services.impl;

import com.lms.lms.dto.QuizScoreDto;
import com.lms.lms.dto.SubmitQuizRequest;
import com.lms.lms.entities.*;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.*;
import com.lms.lms.services.QuizAttemptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizAttemptServiceImpl implements QuizAttemptService {
    private final QuizAttemptRepository attemptRepo;
    private final QuizRepository quizRepo;
    private final UserRepository userRepo;
    private final QuestionRepository questionRepo;
    private final ChoiceRepository choiceRepo;
    private final AnswerRepository answerRepo;

    public QuizAttemptServiceImpl(QuizAttemptRepository attemptRepo,
                                  QuizRepository quizRepo,
                                  UserRepository userRepo,
                                  QuestionRepository questionRepo,
                                  ChoiceRepository choiceRepo,
                                  AnswerRepository answerRepo) {
        this.attemptRepo = attemptRepo;
        this.quizRepo = quizRepo;
        this.userRepo = userRepo;
        this.questionRepo = questionRepo;
        this.choiceRepo = choiceRepo;
        this.answerRepo = answerRepo;
    }

    @Override
    public QuizAttempt submitAttempt(Long userId, Long quizId, SubmitQuizRequest request) {
        if (userId == null || quizId == null) {
            throw new RuntimeException("User id or quiz id is null!");
        }
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        Quiz quiz = quizRepo.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quiz not found: " + quizId));

        QuizAttempt attempt = new QuizAttempt();
        attempt.setUser(user);
        attempt.setQuiz(quiz);
        attempt.setAttemptedAt(LocalDateTime.now());
        attempt = attemptRepo.save(attempt);

        QuizAttempt finalAttempt = attempt;
        List<Answer> answers = request.getAnswers().stream().map(dto -> {
            Question question = questionRepo.findById(dto.getQuestionId()).orElseThrow(() -> new ResourceNotFoundException("Question not found: " + dto.getQuestionId()));
            Choice choice = choiceRepo.findById(dto.getChoiceId()).orElseThrow(() -> new ResourceNotFoundException("Choice not found: " + dto.getChoiceId()));

            Answer ans = new Answer();
            ans.setAttempt(finalAttempt);
            ans.setQuestion(question);
            ans.setChoice(choice);
            ans.setAwardedMarks(question.evaluateAnswer(choice));
            return answerRepo.save(ans);
        }).collect(Collectors.toList());

        attempt.setAnswers(answers);
        return attempt;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizAttempt> listByUser(Long userId) {
        if (userId == null) throw new RuntimeException("User id is null!");
        return attemptRepo.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizAttempt> listByQuiz(Long quizId) {
        if (quizId == null) throw new RuntimeException("Quiz id is null!");
        return attemptRepo.findByQuizId(quizId);
    }

    @Override
    public QuizAttempt getByAttemptId(Long attemptId) {
        if (attemptId == null) throw new RuntimeException("Attempt id is null!");
        return attemptRepo.findById(attemptId).orElseThrow(() -> new ResourceNotFoundException("Quiz attempt not found for quiz attempt id " + attemptId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuizScoreDto> getScoresByUser(Long userId) {
        if (userId == null) throw new RuntimeException("User id is null!");
        return attemptRepo.findByUserId(userId).stream()
                .map(attempt -> {
                    double total = attempt.getAnswers().stream()
                            .mapToDouble(Answer::getAwardedMarks)
                            .sum();
                    return new QuizScoreDto(
                            attempt.getId(),
                            attempt.getQuiz().getId(),
                            attempt.getAttemptedAt(),
                            total
                    );
                })
                .collect(Collectors.toList());
    }
}
