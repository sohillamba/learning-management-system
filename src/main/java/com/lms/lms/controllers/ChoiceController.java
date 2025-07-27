package com.lms.lms.controllers;

import com.lms.lms.entities.Choice;
import com.lms.lms.services.ChoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes/{quizId}/questions/{questionId}/choices")
@Validated
public class ChoiceController {

    private final ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Choice createChoice(@PathVariable Long questionId, @RequestBody Choice choice) {
        return choiceService.create(questionId, choice);
    }

    @GetMapping
    public List<Choice> listChoices(@PathVariable Long questionId) {
        return choiceService.getByQuestionId(questionId);
    }

    @GetMapping("/{id}")
    public Choice getChoice(@PathVariable Long questionId, @PathVariable Long id) {
        return choiceService.getById(id);
    }

    @PutMapping("/{id}")
    public Choice updateChoice(@PathVariable Long questionId, @PathVariable Long id,
                               @RequestBody Choice choice) {
        return choiceService.update(id, choice);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChoice(@PathVariable Long questionId, @PathVariable Long id) {
        choiceService.delete(id);
    }
}
