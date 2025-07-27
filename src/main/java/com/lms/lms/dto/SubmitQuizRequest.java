package com.lms.lms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SubmitQuizRequest {
    private List<AnswerDTO> answers;
}
