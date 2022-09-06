package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionConverter;

import java.util.List;

public class QuestionService {
    private QuestionDao questionDao;

    public void printQuestions() {
        for (String questionStr : getStringQuestions()) {
            Question question = QuestionConverter.convertToQuestion(questionStr);
            System.out.println(question.toString());
        }
    }

    public List<String> getStringQuestions() {
        return questionDao.getQuestions();
    }

    public void setQuestionDao(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }
}
