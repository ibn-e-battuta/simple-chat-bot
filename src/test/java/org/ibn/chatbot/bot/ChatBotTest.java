package org.ibn.chatbot.bot;

import org.ibn.chatbot.quiz.service.QuizService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOutNormalized;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.*;

class ChatBotTest {

    @ParameterizedTest
    @MethodSource("provideAgeAndRemainders")
    void shouldGuessAge(String age, String a, String b, String c) throws Exception {

        String[] expected = {
                "Let me guess your age.",
                "Enter remainders of dividing your age by 3, 5 and 7.",
                "Your age is " + age + ", that's a good time to start programming!"
        };

        withTextFromSystemIn(a, b, c).execute(() -> {
            String[] actual = tapSystemOutNormalized(() -> createBot().guessAge()).split("\n");
            assertArrayEquals(actual, expected);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "5", "25"})
    void shouldCounting(String range) throws Exception {

        List<String> expectedList = new ArrayList<>();
        expectedList.add("Now I will prove to you that I can count to any number you want.");
        expectedList.addAll(IntStream.rangeClosed(0, Integer.parseInt(range))
                .mapToObj(i -> i + "!").toList());

        String[] expected = expectedList.toArray(new String[0]);

        withTextFromSystemIn(range).execute(() -> {
            String[] actual = tapSystemOutNormalized(() -> createBot().count()).split("\n");
            assertArrayEquals(actual, expected);
        });
    }

    private static Stream<Arguments> provideAgeAndRemainders() {
        return Stream.of(
                Arguments.of("22", "1", "2", "1"),
                Arguments.of("10", "1", "0", "3"),
                Arguments.of("83", "2", "3", "6")
        );
    }

    private IBot createBot() {
        return new ChatBot
        (
            new Scanner(System.in),
            System.out::println,
            "Aid",
            2023,
            new QuizService()
        );
    }
}