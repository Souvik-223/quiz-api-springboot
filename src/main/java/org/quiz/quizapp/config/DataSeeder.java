package org.quiz.quizapp.config;

import jakarta.transaction.Transactional;
import org.quiz.quizapp.entity.Question;
import org.quiz.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private QuestionRepository questionRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void seedQuestions() {
        if (questionRepository.count() == 0) {
            List<Question> questions = Arrays.asList(
                    new Question("What is the capital of France?", "Paris",
                            Arrays.asList("London", "Paris", "Berlin", "Madrid")),
                    new Question("Which planet is known as the Red Planet?", "Mars",
                            Arrays.asList("Venus", "Mars", "Jupiter", "Saturn")),
                    new Question("What is 2 + 2?", "4",
                            Arrays.asList("3", "4", "5", "6")),
                    new Question("Who painted the Mona Lisa?", "Leonardo da Vinci",
                            Arrays.asList("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"))
            );

            questionRepository.saveAll(questions);
        }
    }
}
