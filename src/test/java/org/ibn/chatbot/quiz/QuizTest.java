package org.ibn.chatbot.quiz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    @Test
    void shouldThrowQuestionEmpty() {
        Exception exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> Quiz.builder().setQuestion("").build()
        );

        String expected = "Question cannot be empty.";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    void shouldThrowQuestionNull() {
        Exception exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> Quiz.builder().build()
        );

        String expected = "Question cannot be empty.";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    void shouldThrowAnswersNull() {
        Exception exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> Quiz.builder().setQuestion("test").build()
        );

        String expected = "Answers cannot be empty.";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @Test
    void shouldThrowAnswersEmpty() {
        Exception exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> Quiz.builder()
                        .setQuestion("test")
                        .setAnswers(List.of())
                        .build()
        );

        String expected = "Answers cannot be empty";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2, 99})
    void shouldThrowInvalidIndex(int index) {
        Exception exception = Assertions.assertThrows(
                IllegalStateException.class,
                () -> Quiz.builder()
                        .setQuestion("test")
                        .setAnswers(List.of("first", "second"))
                        .setAnswerIndex(index)
                        .build()
        );

        String expected = "Invalid index.";
        String actual = exception.getMessage();
        assertTrue(actual.contains(expected));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void shouldBeOk(int index) {
        String question = "test";
        List<String> answers = List.of("first", "second");

        Quiz quiz = Quiz.builder()
                .setQuestion(question)
                .setAnswers(answers)
                .setAnswerIndex(index)
                .build();

        assertEquals(question, quiz.getQuestion());
        assertEquals(answers, quiz.getAnswers());
        assertEquals(index, quiz.getAnswerIndex());
    }
}