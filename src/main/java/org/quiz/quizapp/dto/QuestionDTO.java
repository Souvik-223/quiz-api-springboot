package org.quiz.quizapp.dto;

import lombok.Data;
import org.quiz.quizapp.entity.Question;

import java.util.List;

@Data
public class QuestionDTO {

    private Long id;
    private String text;
    private List<String> options;

    public static QuestionDTO fromQuestion(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setOptions(question.getOptions());
        return dto;
    }
}
