package com.example.LMS.RequestDto;

import lombok.Data;
// we can use this classDto only when we make age unique and do validation on the basis of age.
@Data
public class AuthorAddRequestDto {
    private Integer authorId;

    private String name;

    private String emailId;

    private Integer age;

    private String penName;
}
