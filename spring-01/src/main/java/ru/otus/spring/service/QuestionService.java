package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

public class QuestionService {
    private QuestionDao questionDao;

    public void printQuestion() {
        for (Question question : questionDao.getQuestions()) {
            System.out.println(question.toString());
        }
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }
}
