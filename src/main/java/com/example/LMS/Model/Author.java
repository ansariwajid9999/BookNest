package com.example.LMS.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    private String name;

    private String emailId;

    @Column(unique = true)
    private Integer age;

    private String penName;

//    public Author(Integer authorId, String name, String emailId, Integer age, String penName) {
//        this.authorId = authorId;
//        this.name = name;
//        this.emailId = emailId;
//        this.age = age;
//        this.penName = penName;
//    }

    @OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
    private List<Book> bookList = new ArrayList<>();
}
