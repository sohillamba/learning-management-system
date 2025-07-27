package com.lms.lms.repo;

import com.lms.lms.entities.lessonProgress.LessonProgress;
import com.lms.lms.entities.lessonProgress.LessonProgressId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, LessonProgressId> {

    @Query(value =
            "SELECT CASE WHEN COUNT(lp.*)=0 THEN 0.0 " +
                    "ELSE SUM((lp.completed)::int) * 100.0 / COUNT(lp.*) END " +
                    "FROM lesson_progress lp " +
                    "JOIN lessons l ON lp.lesson_id = l.id " +
                    "WHERE lp.user_id = ?1 AND l.course_id = ?2",
            nativeQuery = true)
    double countByUserIdAndLessonCourseIdAndCompletedTrue(Long userId, Long courseId);

    List<LessonProgress> findByUserId(Long userId);

    List<LessonProgress> findByLessonCourseIdAndUserId(Long courseId, Long userId);
}
