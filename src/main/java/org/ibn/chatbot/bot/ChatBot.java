package org.ibn.chatbot.bot;

import org.ibn.chatbot.quiz.Quiz;
import org.ibn.chatbot.quiz.service.IQuizService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class ChatBot implements IBot {

    private final Scanner scanner;
    private final Consumer<String> print;
    private final String name;
    private final int year;
    private final IQuizService service;

    public ChatBot(Scanner scanner, Consumer<String> print, String name, int year, IQuizService service) {
        this.scanner = scanner;
        this.print = print;
        this.name = name;
        this.year = year;
        this.service = service;
    }

    @Override
    public void greet() {
        print.accept("Hello! My name is " + name + ".");
        print.accept("I was created in " + year + ".");
        print.accept("Please, remind me your name.");
        print.accept("What a great name you have, " + scanner.nextLine() + "!");
    }

    @Override
    public void guessAge() {
        print.accept("Let me guess your age.");
        print.accept("Enter remainders of dividing your age by 3, 5 and 7.");
        int age = (scanner.nextInt() * 70 + scanner.nextInt() * 21 + scanner.nextInt() * 15) % 105;
        print.accept("Your age is " + age + ", that's a good time to start programming!");
    }

    @Override
    public void count() {
        print.accept("Now I will prove to you that I can count to any number you want.");
        IntStream.rangeClosed(0, scanner.nextInt()).forEach(i -> print.accept(i + "!"));
    }

    @Override
    public void quiz() {

        Quiz quiz = Quiz.builder()
                .setQuestion("Why do we use methods?")
                .setAnswers(List.of(
                        "To repeat a statement multiple times.",
                        "To decompose a program into several small subroutines.",
                        "To determine the execution time of a program.",
                        "To interrupt the execution of a program."))
                .setAnswerIndex(1)
                .build();

        service.ask(quiz, print, scanner);
    }

    @Override
    public void bye() {
        print.accept("Congratulations, have a nice day!");
    }
}

