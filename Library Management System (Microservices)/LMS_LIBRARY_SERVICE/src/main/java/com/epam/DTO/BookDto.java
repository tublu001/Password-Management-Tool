package com.epam.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class BookDto
{
    private long bookId;
    private String bookName;
    private String bookPublisher;
    private String bookAuthor;

    public BookDto(String bookName, String bookPublisher, String bookAuthor)
    {
        this.bookName = bookName;
        this.bookPublisher = bookPublisher;
        this.bookAuthor = bookAuthor;
    }

    @Override
    public String toString()
    {
        return "BookDto{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookPublisher='" + bookPublisher + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}
