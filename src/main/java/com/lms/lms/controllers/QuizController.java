package com.lms.lms.controllers;

import com.lms.lms.entities.Quiz;
import com.lms.lms.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/quizzes")
@Validated
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz createQuiz(@PathVariable Long courseId, @RequestBody Quiz quiz) {
        return quizService.create(courseId, quiz);
    }

    @GetMapping
    public List<Quiz> listQuizzes(@PathVariable Long courseId) {
        return quizService.getByCourseId(courseId);
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long courseId, @PathVariable Long id) {
        return quizService.getById(id);
    }

    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long courseId, @PathVariable Long id, @RequestBody Quiz quiz) {
        return quizService.update(id, quiz);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable Long courseId, @PathVariable Long id) {
        quizService.delete(id);
    }
}
