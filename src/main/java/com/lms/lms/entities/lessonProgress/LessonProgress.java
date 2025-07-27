package com.lms.lms.entities.lessonProgress;

import com.lms.lms.entities.Lesson;
import com.lms.lms.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lesson_progress")
public class LessonProgress {
    @EmbeddedId
    private LessonProgressId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lessonId")
    private Lesson lesson;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
