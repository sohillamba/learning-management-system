package com.lms.lms.controllers;

import com.lms.lms.dto.QuizScoreDto;
import com.lms.lms.dto.SubmitQuizRequest;
import com.lms.lms.entities.QuizAttempt;
import com.lms.lms.services.QuizAttemptService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/attempts")
public class QuizAttemptController {
    private final QuizAttemptService attemptService;

    public QuizAttemptController(QuizAttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @GetMapping("/{attemptId}")
    public QuizAttempt getQuizAttemptById(@PathVariable Long attemptId) {
        return attemptService.getByAttemptId(attemptId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizAttempt submit(@PathVariable Long quizId, @RequestParam Long userId, @RequestBody SubmitQuizRequest request) {
        return attemptService.submitAttempt(userId, quizId, request);
    }

    @GetMapping("/user/{userId}")
    public List<QuizAttempt> userAttempts(@PathVariable Long quizId, @PathVariable Long userId) {
        return attemptService.listByUser(userId);
    }

    @GetMapping
    public List<QuizAttempt> quizAttempts(@PathVariable Long quizId) {
        return attemptService.listByQuiz(quizId);
    }

    @GetMapping("/user/{userId}/scores")
    public List<QuizScoreDto> getUserScores(@PathVariable Long userId) {
        return attemptService.getScoresByUser(userId);
    }
}
