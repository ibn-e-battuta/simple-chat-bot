package org.ibn.chatbot;

import org.ibn.chatbot.bot.ChatBot;
import org.ibn.chatbot.bot.IBot;
import org.ibn.chatbot.quiz.service.IQuizService;
import org.ibn.chatbot.quiz.service.QuizService;

import java.util.Scanner;
import java.util.function.Consumer;

public class ChatBotApplication {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final Consumer<String> print = System.out::println;
        final String name = "Aid";
        final int year = 2023;
        final IQuizService service = new QuizService();

        // run chatbot
        IBot bot = new ChatBot(scanner, print, name, year, service);
        bot.run();

        scanner.close();
    }
}