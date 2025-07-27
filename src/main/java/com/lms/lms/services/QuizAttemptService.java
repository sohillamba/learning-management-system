package com.lms.lms.services;

import com.lms.lms.dto.QuizScoreDto;
import com.lms.lms.dto.SubmitQuizRequest;
import com.lms.lms.entities.QuizAttempt;

import java.util.List;

public interface QuizAttemptService {

    QuizAttempt submitAttempt(Long userId, Long quizId, SubmitQuizRequest request);

    List<QuizAttempt> listByUser(Long userId);

    List<QuizAttempt> listByQuiz(Long quizId);

    QuizAttempt getByAttemptId(Long attemptId);

    List<QuizScoreDto> getScoresByUser(Long userId);
}
