package org.ibn.chatbot.quiz;

import java.util.List;

public final class Quiz {

    private final String question;
    private final List<String> answers;
    private final int answerIndex;

    private Quiz(String question, List<String> answers, int answerIndex) {
        this.question = question;
        this.answers = answers;
        this.answerIndex = answerIndex;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    /**
     * Quiz builder with validation
     */
    public static final class Builder {

        private String question;
        private List<String> answers;
        private int answerIndex;

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setAnswers(List<String> answers) {
            this.answers = answers;
            return this;
        }

        public Builder setAnswerIndex(int answerIndex) {
            this.answerIndex = answerIndex;
            return this;
        }

        public Quiz build() {

            if (question == null || question.isEmpty()) {
                throw new IllegalStateException("Question cannot be empty.");
            }

            if (answers == null || answers.isEmpty()) {
                throw new IllegalStateException("Answers cannot be empty.");
            }

            if (answerIndex >= answers.size() || answerIndex < 0) {
                throw new IllegalStateException("Invalid index.");
            }

            return new Quiz(question, answers, answerIndex);
        }
    }
}
