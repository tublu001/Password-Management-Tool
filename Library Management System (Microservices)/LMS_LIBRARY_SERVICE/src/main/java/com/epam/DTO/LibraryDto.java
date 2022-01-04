package com.epam.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class LibraryDto
{
    private long id;
    private String userName;
    private Long bookId;

    public LibraryDto(String userName, Long bookId)
    {
        this.userName = userName;
        this.bookId = bookId;
    }

    @Override
    public String toString()
    {
        return "LibraryDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
