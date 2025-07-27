package com.lms.lms.controllers;

import com.lms.lms.entities.Course;
import com.lms.lms.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Validated
public class CourseController {

    public final CourseService courseService;

    public CourseController (CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        return courseService.create(course);
    }

    @GetMapping
    public List<Course> listCourses() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @GetMapping("/{id}/content")
    public Course getCourseWithContent(@PathVariable Long id) {
        return courseService.getWithContent(id);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }
}
