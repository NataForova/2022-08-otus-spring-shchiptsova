package ru.otus.spring.domain;

public class Question {
    String question;
    String optionA;
    String optionB;

    public Question(String question, String optionA, String optionB) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    @Override
    public String toString() {
        return  "" + question + "? \n" +
                "A) " + optionA + '\n' +
                "B) " + optionB + '\n';
    }
}
