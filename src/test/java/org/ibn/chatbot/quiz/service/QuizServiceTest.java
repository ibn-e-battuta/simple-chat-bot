package org.ibn.chatbot.quiz.service;

import org.ibn.chatbot.quiz.Quiz;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.*;

class QuizServiceTest {

    @Test
    void shouldOutputMatch() throws Exception {
        IQuizService service = new QuizService();
        Quiz quiz = Quiz.builder()
                .setQuestion("is this test")
                .setAnswers(List.of("no", "i don't know", "yes"))
                .setAnswerIndex(2)
                .build();

        String[] expected = {
                "is this test",
                "1. no",
                "2. i don't know",
                "3. yes",
                "Please, try again.",
                "Please, try again.",
                "Correct answer!"
        };

        withTextFromSystemIn("1", "99", "3").execute(() -> {
            String[] actual = tapSystemOutNormalized(() ->
                    service.ask(quiz, System.out::println, new Scanner(System.in))
            ).split("\n");

            assertArrayEquals(expected, actual);
        });
    }
}