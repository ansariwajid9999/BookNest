package com.example.LMS.Repository;

import com.example.LMS.Enum.Genre;
import com.example.LMS.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findBookByGenre(Genre genre);
    Optional<List<Book>> findBookListByGenre(Genre genre);
}
