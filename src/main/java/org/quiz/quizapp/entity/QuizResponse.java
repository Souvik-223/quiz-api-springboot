package org.quiz.quizapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponse {

    private Question question;
    private boolean isCorrect;
    private QuizSession quizSession;
}
