package org.ibn.chatbot.quiz.service;

import org.ibn.chatbot.quiz.Quiz;

import java.util.Scanner;
import java.util.function.Consumer;

@FunctionalInterface
public interface IQuizService {
    void ask(Quiz quiz, Consumer<String> print, Scanner scanner);
}