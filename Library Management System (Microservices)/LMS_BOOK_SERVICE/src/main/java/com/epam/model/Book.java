package com.epam.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data
class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId", nullable = false)
    private Long bookId = 0L;
    private String bookName;
    private String bookPublisher;
    private String bookAuthor;


    public Book(String bookName, String bookPublisher, String bookAuthor)
    {
        this.bookName = bookName;
        this.bookPublisher = bookPublisher;
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookPublisher='" + bookPublisher + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}
