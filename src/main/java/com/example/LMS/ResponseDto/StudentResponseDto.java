package com.example.LMS.ResponseDto;

import com.example.LMS.Enum.Department;
import com.example.LMS.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class StudentResponseDto {
    private Integer rollNo;

    private String name;

    private Integer age;

    private Gender gender;

    private Department department;

    private String emailId;

    public StudentResponseDto(Integer rollNo, Integer age, String name, String emailId, Department department, Gender gender) {
        this.rollNo = rollNo;
        this.age = age;
        this.name = name;
        this.emailId = emailId;
        this.department = department;
        this.gender = gender;
    }
}
