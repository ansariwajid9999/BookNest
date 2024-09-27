package com.example.LMS.Repository;

import com.example.LMS.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author , Integer> {
    //Optional<Author> findByAge(Integer age);
}
