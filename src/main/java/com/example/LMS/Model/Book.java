package com.example.LMS.Model;

import com.example.LMS.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(unique = true)
    private String title;

    private Boolean isAvailable;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    private Date publicationDate;

    private Integer price;

    public Book(){

    }

    public Book(String title, Boolean isAvailable, Genre genre, Date publicationDate, Integer price) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.price = price;
    }

    // When List<Book> list = author.getBookList();  book has author entity which has author_id
    // results infinite recursion , so add @JsonIgnore
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Author author;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();
}
