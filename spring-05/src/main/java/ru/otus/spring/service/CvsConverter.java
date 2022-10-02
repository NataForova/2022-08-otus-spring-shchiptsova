package ru.otus.spring.service;

import ru.otus.spring.exceptions.CsvFormatConvertException;
import java.util.List;

public interface CvsConverter<T> {
    List<T> convertCvsToQuestionList(List<String> cvsStringList) throws CsvFormatConvertException;
    T convertCvsToQuestion(String cvsString) throws CsvFormatConvertException;
}
