package com.lms.lms.entities.enrollment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.lms.entities.Course;
import com.lms.lms.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "enrollments")
@NoArgsConstructor
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id = new EnrollmentId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId")
    @JsonIgnore
    private Course course;

    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;
}
