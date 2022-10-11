package ru.otus.spring.domain;

public class Question {
    private final String question;
    private final String optionA;
    private final String optionB;
    private final String rightAnswer;

    public Question(String question, String optionA, String optionB, String rightAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.rightAnswer = rightAnswer;
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

    public String getRightAnswer() {
        return rightAnswer;
    }

    @Override
    public String toString() {
        return  "" + question + "? \n" +
                "A) " + optionA + '\n' +
                "B) " + optionB + '\n';
    }
}
