package org.quiz.quizapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private String correctAnswer;

    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "options")
    private List<String> options;

    public Question(String text, String correctAnswer, List<String> options) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }
}
