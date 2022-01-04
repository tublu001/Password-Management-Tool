package com.epam.Repository;

import com.epam.DTO.BookDto;
import com.epam.Model.Book;
import com.epam.Model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long>
{
    Optional<Library> findByUserNameAndBookId(String userName, Long bookId);
    List<Library> findByUserName(String userName);
}
