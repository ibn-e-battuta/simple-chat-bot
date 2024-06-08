package org.ibn.chatbot.bot;

public interface IBot {

    void greet();
    void guessAge();
    void count();
    void quiz();
    void bye();

    default void run() {
        greet();
        guessAge();
        count();
        quiz();
        bye();
    }
}