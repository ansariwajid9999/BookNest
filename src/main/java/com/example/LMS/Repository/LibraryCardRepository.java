package com.example.LMS.Repository;

import com.example.LMS.Model.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard , Integer> {
    Optional<List<LibraryCard>> findByCardStatusIn(List<String> list);

    // if you have null in your card status , if card status is not enum then it definitely  has null
    //@Query("SELECT l FROM LibraryCard l WHERE l.cardStatus IN (:statuses) OR l.cardStatus IS NULL")
    //Optional<List<LibraryCard>> findByCardStatusIn(@Param("statuses") List<String> statuses);

}
