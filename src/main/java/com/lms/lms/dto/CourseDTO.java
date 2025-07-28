package com.lms.lms.dto;

import com.lms.lms.entities.Lesson;
import com.lms.lms.entities.Quiz;
import com.lms.lms.entities.enrollment.Enrollment;
import lombok.Data;

import java.util.Set;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String instructorName;
    private Double price;
    private Set<Lesson> lessons;
    private Set<Quiz> quizzes;
    private Set<Enrollment> enrollments;
}
