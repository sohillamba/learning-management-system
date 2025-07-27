package com.lms.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class QuizScoreDto {
    private Long attemptId;
    private Long quizId;
    private LocalDateTime attemptedAt;
    private double totalScore;
}
