package org.quiz.quizapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz_session")
@Data
public class QuizSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private int totalQuestions;
    @Getter
    private int correctAnswers;
    @Getter
    private int incorrectAnswers;

    @ElementCollection
    private List<Long> answeredQuestionsId = new ArrayList<>();

    public void incrementCorrect() {
        this.correctAnswers++;
    }

    public void incrementIncorrect() {
        this.incorrectAnswers++;
    }

    public void addAnsweredQuestionId(Long questionId) {
        this.answeredQuestionsId.add(questionId);
    }

}
