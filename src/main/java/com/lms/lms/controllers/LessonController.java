package com.lms.lms.controllers;

import com.lms.lms.entities.Lesson;
import com.lms.lms.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons")
@Validated
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lesson createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        return lessonService.create(courseId, lesson);
    }

    @GetMapping
    public List<Lesson> listLessons(@PathVariable Long courseId) {
        return lessonService.getByCourseId(courseId);
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable Long courseId, @PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PutMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long courseId, @PathVariable Long id,
                               @RequestBody Lesson lesson) {
        return lessonService.update(id, lesson);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLesson(@PathVariable Long courseId, @PathVariable Long id) {
        lessonService.delete(id);
    }
}
