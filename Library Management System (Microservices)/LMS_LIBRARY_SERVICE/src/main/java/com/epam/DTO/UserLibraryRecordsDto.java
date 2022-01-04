package com.epam.DTO;

import com.epam.Model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public @Data class UserLibraryRecordsDto
{
    private String userName;
    private String userEmail;
    private String name;
    private List<BookDto> issuedBooks = new ArrayList<>();

    @Override
    public String toString()
    {
        return "UserLibraryRecordsDto{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", name='" + name + '\'' +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
