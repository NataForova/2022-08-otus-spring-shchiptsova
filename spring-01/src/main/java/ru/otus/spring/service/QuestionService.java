package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;

public class QuestionService {
    private QuestionDao questionDao;

    public void printQuestions() {
        for (Question question : getQuestions()) {
            System.out.println(question.toString());
        }
    }

    public List<Question> getQuestions() {
        return questionDao.getQuestions();
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }
}
