package com.epam.DTO;

import com.epam.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.epam.utils.Constants.*;

public @Data class BookDto
{
    private long bookId;

    @NotBlank(message = BOOK_NAME_MANDATORY)
    private String bookName;
    @NotBlank(message = BOOK_PUBLISHER_NAME_MANDATORY)
    private String bookPublisher;
    @NotBlank(message = BOOK_AUTHOR_NAME_MANDATORY)
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
