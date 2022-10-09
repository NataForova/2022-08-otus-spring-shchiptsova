package ru.otus.spring.command;

import ru.otus.spring.domain.Book;

public interface ApplicationCommand {
    int countBooks();
    int insertBook(String bookNane, int authorId, int genreId);

    String getBookById(int bookId);


}
