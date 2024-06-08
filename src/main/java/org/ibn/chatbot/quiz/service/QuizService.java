package org.ibn.chatbot.quiz.service;

import org.ibn.chatbot.quiz.Quiz;

import java.util.Scanner;
import java.util.function.Consumer;

public class QuizService implements IQuizService {
    @Override
    public void ask(Quiz quiz, Consumer<String> print, Scanner scanner) {
        print.accept(quiz.getQuestion());

        int index = 1;
        for (String answer : quiz.getAnswers()) {
            print.accept(index + ". " + answer);
            index++;
        }

        while ((scanner.nextInt() - 1) != quiz.getAnswerIndex()) {
            print.accept("Please, try again.");
        }
        print.accept("Correct answer!");
    }
}
