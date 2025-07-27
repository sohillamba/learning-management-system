package com.lms.lms.controllers;

import com.lms.lms.entities.lessonProgress.LessonProgress;
import com.lms.lms.services.LessonProgressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class LessonProgressController {
    private final LessonProgressService lpService;

    public LessonProgressController(LessonProgressService lpService) {
        this.lpService = lpService;
    }

    @PostMapping("/complete")
    @ResponseStatus(HttpStatus.CREATED)
    public LessonProgress complete(@RequestParam Long userId, @RequestParam Long lessonId) {
        return lpService.markCompleted(userId, lessonId);
    }

    @PostMapping("/incomplete")
    public LessonProgress incomplete(@RequestParam Long userId, @RequestParam Long lessonId) {
        return lpService.markIncomplete(userId, lessonId);
    }

    @GetMapping("/user/{userId}")
    public List<LessonProgress> progressByUser(@PathVariable Long userId) {
        return lpService.getProgressByUser(userId);
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    public List<LessonProgress> progressByCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        return lpService.getProgressByCourse(userId, courseId);
    }

    @GetMapping("/user/{userId}/course/{courseId}/percentage")
    public double getCourseProgress(@PathVariable Long userId, @PathVariable Long courseId) {
        return lpService.getCourseProgressPercent(userId, courseId);
    }
}
