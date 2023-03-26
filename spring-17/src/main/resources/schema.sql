DROP TABLE IF EXISTS BOOKS CASCADE;
DROP TABLE IF EXISTS GENRES CASCADE;
DROP TABLE IF EXISTS AUTHORS CASCADE;
CREATE TABLE AUTHORS
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE GENRES
(
    ID   BIGINT PRIMARY KEY,
    NAME VARCHAR(255)
);
CREATE TABLE BOOKS
(
    ID        BIGINT PRIMARY KEY,
    NAME      VARCHAR(255),
    AUTHOR_ID BIGINT,
    GENRE_ID  BIGINT,
    foreign key (AUTHOR_ID) references AUTHORS (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (GENRE_ID) references GENRES (ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE COMMENTS
(
    ID        BIGINT PRIMARY KEY,
    COMMENT_TEXT      VARCHAR(255),
    BOOK_ID BIGINT references BOOKS(ID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE "GENRE_ID_SEQUENCE"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 6;

CREATE SEQUENCE "AUTHOR_ID_SEQUENCE"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 4;

CREATE SEQUENCE "BOOK_ID_SEQUENCE"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 8;

CREATE SEQUENCE "COMMENT_ID_SEQUENCE"
    MINVALUE 1
    MAXVALUE 999999999
    INCREMENT BY 1
    START WITH 3;

create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);