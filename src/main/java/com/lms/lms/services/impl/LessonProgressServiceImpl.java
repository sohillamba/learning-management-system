package com.lms.lms.services.impl;

import com.lms.lms.entities.Lesson;
import com.lms.lms.entities.User;
import com.lms.lms.entities.lessonProgress.LessonProgress;
import com.lms.lms.entities.lessonProgress.LessonProgressId;
import com.lms.lms.exception.ResourceNotFoundException;
import com.lms.lms.repo.LessonProgressRepository;
import com.lms.lms.repo.LessonRepository;
import com.lms.lms.repo.UserRepository;
import com.lms.lms.services.LessonProgressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LessonProgressServiceImpl implements LessonProgressService {
    private final LessonProgressRepository lpRepo;
    private final UserRepository userRepo;
    private final LessonRepository lessonRepo;

    public LessonProgressServiceImpl(LessonProgressRepository lpRepo,
                                     UserRepository userRepo,
                                     LessonRepository lessonRepo) {
        this.lpRepo = lpRepo;
        this.userRepo = userRepo;
        this.lessonRepo = lessonRepo;
    }

    @Override
    public LessonProgress markCompleted(Long userId, Long lessonId) {
        if (userId == null || lessonId == null) throw new IllegalArgumentException("User ID and Lesson ID must not be null");
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));
        Lesson lesson = lessonRepo.findById(lessonId).orElseThrow(() -> new ResourceNotFoundException("Lesson not found: " + lessonId));
        LessonProgressId id = new LessonProgressId(userId, lessonId);
        LessonProgress lp = lpRepo.findById(id).orElse(new LessonProgress());
        lp.setId(id);
        lp.setUser(user);
        lp.setLesson(lesson);
        lp.setCompleted(true);
        lp.setCompletedAt(LocalDateTime.now());
        return lpRepo.save(lp);
    }

    @Override
    public LessonProgress markIncomplete(Long userId, Long lessonId) {
        if (userId == null || lessonId == null) throw new IllegalArgumentException("User ID and Lesson ID must not be null");
        LessonProgressId id = new LessonProgressId(userId, lessonId);
        LessonProgress lp = lpRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Progress not found"));
        lp.setCompleted(false);
        lp.setCompletedAt(null);
        return lpRepo.save(lp);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonProgress> getProgressByUser(Long userId) {
        if (userId == null) throw new IllegalArgumentException("User ID must not be null");
        return lpRepo.findByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonProgress> getProgressByCourse(Long userId, Long courseId) {
        if (userId == null || courseId == null) throw new IllegalArgumentException("User ID and Course ID must not be null");
        return lpRepo.findByLessonCourseIdAndUserId(courseId, userId);
    }

    @Override
    public double getCourseProgressPercent(Long userId, Long courseId) {
        if (userId == null || courseId == null) throw new IllegalArgumentException("User ID and Course ID must not be null");
        return lpRepo.countByUserIdAndLessonCourseIdAndCompletedTrue(userId, courseId);

//        long totalLessons = lessonRepo.countByCourseId(courseId);
//        if (totalLessons == 0) return 0.0;
//        long completed = lpRepo.findByLessonCourseIdAndUserId(courseId, userId).stream().filter(LessonProgress::isCompleted).count();
//        return (completed * 100.0) / totalLessons;
    }
}
