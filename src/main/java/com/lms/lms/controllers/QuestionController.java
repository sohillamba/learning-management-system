package com.lms.lms.controllers;

import com.lms.lms.entities.Question;
import com.lms.lms.services.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions")
@Validated
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question createQuestion(@PathVariable Long quizId, @RequestBody Question question) {
        return questionService.create(quizId, question);
    }

    @GetMapping
    public List<Question> listQuestions(@PathVariable Long quizId) {
        return questionService.getByQuizId(quizId);
    }

    @GetMapping("/{id}")
    public Question getQuestion(@PathVariable Long quizId, @PathVariable Long id) {
        return questionService.getById(id);
    }

    @PutMapping("/{id}")
    public Question updateQuestion(@PathVariable Long quizId, @PathVariable Long id,
                                   @RequestBody Question question) {
        return questionService.update(id, question);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestion(@PathVariable Long quizId, @PathVariable Long id) {
        questionService.delete(id);
    }
}
