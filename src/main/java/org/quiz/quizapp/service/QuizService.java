package org.quiz.quizapp.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.quiz.quizapp.entity.Question;
import org.quiz.quizapp.entity.QuizResponse;
import org.quiz.quizapp.entity.QuizSession;
import org.quiz.quizapp.entity.QuizSummary;
import org.quiz.quizapp.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;
    private final EntityManager entityManager;

    @Transactional
    public QuizSession startNewQuizSession() {
        try {
            QuizSession session = new QuizSession();
            session.setTotalQuestions(0);
            session.setCorrectAnswers(0);
            session.setIncorrectAnswers(0);
            entityManager.persist(session);
            return session;
        } catch (Exception e) {
            throw new RuntimeException("Failed to start quiz session", e);
        }
    }

    @Transactional
    public Question getRandomQuestion(QuizSession session) {
        try {
            Question randomQuestion = questionRepository.findRandomQuestion();
            if (randomQuestion == null) {
                throw new RuntimeException("No question available");
            }
            session.setTotalQuestions(session.getTotalQuestions() + 1);
            entityManager.merge(session);
            return randomQuestion;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get random question", e);
        }
    }

    @Transactional
    public QuizResponse submitAnswer(QuizSession session, Long questionId, String selectedAnswer) {
        try {
            Optional<Question> questionOpt = questionRepository.findById(questionId);

            if (questionOpt.isEmpty()) {
                throw new RuntimeException("Question not found");
            }

            Question question = questionOpt.get();
            boolean isCorrect = question.getCorrectAnswer().equals(selectedAnswer);

            if (isCorrect) {
                session.incrementCorrect();
            } else {
                session.incrementIncorrect();
            }

            session.addAnsweredQuestionId(questionId);
            entityManager.merge(session);

            return new QuizResponse(question, isCorrect, session);
        } catch (Exception e) {
            throw new RuntimeException("Failed to submit answer", e);
        }
    }

    public QuizSummary getQuizSummary(QuizSession session) {
        try {
            int total = session.getTotalQuestions();
            int correct = session.getCorrectAnswers();
            int incorrect = session.getIncorrectAnswers();
            double accuracy = total > 0 ? (correct * 100.0) / total : 0.0;

            return new QuizSummary(total, correct, incorrect, accuracy);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get quiz summary", e);
        }
    }
}
