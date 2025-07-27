package com.lms.lms.services.impl;

import com.lms.lms.entities.Course;
import com.lms.lms.entities.Quiz;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.CourseRepository;
import com.lms.lms.repo.QuizRepository;
import com.lms.lms.services.QuizService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepo;
    private final CourseRepository courseRepo;

    public QuizServiceImpl(QuizRepository quizRepo, CourseRepository courseRepo) {
        this.quizRepo = quizRepo;
        this.courseRepo = courseRepo;
    }

    @Override
    public Quiz create(Long courseId, Quiz quiz) {
        if (courseId == null) throw new IllegalArgumentException("Course ID must not be null");
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + courseId));
        quiz.setCourse(course);
        return quizRepo.save(quiz);
    }

    @Override
    public Quiz getById(Long id) {
        if (id == null) throw new IllegalArgumentException("Quiz ID must not be null");
        return quizRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id " + id));
    }

    @Override
    public List<Quiz> getByCourseId(Long courseId) {
        if (courseId == null) throw new IllegalArgumentException("Course ID must not be null");
        return quizRepo.findByCourseId(courseId);
    }

    @Override
    public Quiz update(Long id, Quiz quiz) {
        if (id == null) throw new IllegalArgumentException("Quiz ID must not be null for update");
        Quiz existing = getById(id);
        existing.setTitle(quiz.getTitle());
        // update questions list if needed
        return quizRepo.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (id == null) throw new IllegalArgumentException("Quiz ID must not be null for delete");
        quizRepo.deleteById(id);
    }
}
