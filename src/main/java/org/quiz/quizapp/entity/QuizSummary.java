package org.quiz.quizapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSummary {

    private int totalQuestions;
    private int correctAnswers;
    private int incorrectAnswers;
    private double accuracy;
}
