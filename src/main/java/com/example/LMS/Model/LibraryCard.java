package com.example.LMS.Model;

import com.example.LMS.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer LibraryCardId;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private Integer noOfBookIssued;

    @OneToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "libraryCard" , cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
