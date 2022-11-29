insert into AUTHORS (id, `name`)
values (1, 'Joanne Rowling');
insert into AUTHORS (id, `name`)
values (2, 'Charles Dickens');
insert into AUTHORS (id, `name`)
values (3, 'Arthur Conan Doyle');

insert into GENRES (id, `name`)
values (1, 'Fantasy');
insert into GENRES (id, `name`)
values (2, 'Comedy');
insert into GENRES (id, `name`)
values (3, 'Science fiction');
insert into GENRES (id, `name`)
values (4, 'Detective');
insert into GENRES (id, `name`)
values (5, 'Novel');

insert into BOOKS (id, `name`, author_id, genre_id)
values (1, 'Sherlock Holmes', 3, 4);
insert into BOOKS (id, `name`, author_id, genre_id)
values (2, 'Lost World', 3, 3);
insert into BOOKS (id, `name`, author_id, genre_id)
values (3, 'Harry Potter', 1, 1);
insert into BOOKS (id, `name`, author_id, genre_id)
values (4, 'The Casual Vacancy', 1, 2);
insert into BOOKS (id, `name`, author_id, genre_id)
values (5, 'Oliver Twist', 2, 5);
insert into BOOKS (id, `name`, author_id, genre_id)
values (6, 'The Mystery of Edwin Droods', 2, 5);
insert into BOOKS (id, `name`, author_id, genre_id)
values (7, 'David Copperfield', 2, 5);

insert into COMMENTS (id, `comment_text`, book_id)
values (1, 'Good Book', 1);
insert into COMMENTS (id, `comment_text`, book_id)
values (2, 'I like it!', 1);
insert into COMMENTS (id, `comment_text`, book_id)
values (3, 'I hate it', 2);
