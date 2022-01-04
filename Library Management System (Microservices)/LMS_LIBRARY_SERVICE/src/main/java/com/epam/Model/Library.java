package com.epam.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data class Library
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "libraryId", nullable = false)
    private long id;
    private String userName;
    private Long bookId;

    public Library(String userName, Long bookId)
    {
        this.userName = userName;
        this.bookId = bookId;
    }

    @Override
    public String toString()
    {
        return "Library{" +
                "userName='" + userName + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
