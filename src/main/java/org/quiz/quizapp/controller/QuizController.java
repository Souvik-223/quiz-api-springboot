package org.quiz.quizapp.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.quiz.quizapp.dto.QuestionDTO;
import org.quiz.quizapp.entity.Question;
import org.quiz.quizapp.entity.QuizResponse;
import org.quiz.quizapp.entity.QuizSession;
import org.quiz.quizapp.entity.QuizSummary;
import org.quiz.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/start")
    public ResponseEntity<QuizSession> startQuiz() {

        try {
            QuizSession quizSession = quizService.startNewQuizSession();
            return ResponseEntity.ok(quizSession);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/question/{sessionId}")
    public ResponseEntity<QuestionDTO> getRandomQuestion(@PathVariable Long sessionId) {
        try {
            QuizSession session = findSessionById(sessionId);
            Question question = quizService.getRandomQuestion(session);
            return ResponseEntity.ok(QuestionDTO.fromQuestion(question));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResponse> submitAnswer(
            @RequestParam Long sessionId,
            @RequestParam Long questionId,
            @RequestParam String selectedAnswer
    ) {
        try {
            QuizSession session = findSessionById(sessionId);
            QuizResponse response = quizService.submitAnswer(session, questionId, selectedAnswer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/summary/{sessionId}")
    public ResponseEntity<?> getQuizSummary(@PathVariable Long sessionId) {
        try {
            QuizSession session = findSessionById(sessionId);
            QuizSummary summary = quizService.getQuizSummary(session);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private QuizSession findSessionById(Long sessionId) {
        QuizSession session = entityManager.find(QuizSession.class, sessionId);
        if (session == null) {
            throw new RuntimeException("Session not found");
        }
        return session;
    }

}
