package com.lms.lms.entities.lessonProgress;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.lms.entities.Lesson;
import com.lms.lms.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lesson_progress")
@NoArgsConstructor
public class LessonProgress {
    @EmbeddedId
    private LessonProgressId id = new LessonProgressId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("lessonId")
    @JsonIgnore
    private Lesson lesson;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
