package ru.otus.spring.domain;

public class Question {
    private final String question;
    private final String optionA;
    private final String optionB;

    public Question(String question, String optionA, String optionB) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    @Override
    public String toString() {
        return  "" + question + "? \n" +
                "A) " + optionA + '\n' +
                "B) " + optionB + '\n';
    }
}
