package ru.otus.spring.command;

public interface ApplicationCommand {
    int countBooks();
    int insertBook(String bookNane, int authorId, int genreId);
    String getBookById(int bookId);
    String getBookByAuthorId(int authorId);
    String getBookByGenreId(int genreId);
    void deleteBook(int bookId);
    String getAllBooks();
    int updateBook(long id, String name, long author_id, long genre_id);
    int insertGenre(String name);
    int insertAuthor(String name);

}
