package com.lms.lms.repo;

import com.lms.lms.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.lessons LEFT JOIN FETCH c.quizzes WHERE c.id = ?1")
    Optional<Course> findByIdWithContent(Long id);
}
