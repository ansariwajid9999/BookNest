package com.example.LMS.Model;

import com.example.LMS.Enum.Department;
import com.example.LMS.Enum.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rollNo;

    private String name;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    private Department department;

    @Column(unique = true)
    private String emailId;

    @OneToOne(mappedBy = "student" , cascade = CascadeType.ALL)
    private LibraryCard libraryCard;
}
