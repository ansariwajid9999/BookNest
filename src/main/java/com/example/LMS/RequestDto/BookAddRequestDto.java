package com.example.LMS.RequestDto;

import com.example.LMS.Enum.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class BookAddRequestDto {

    private String title;

    private Boolean isAvailable;

    private Genre genre;

    private Date publicationDate;

    private Integer price;

    private Integer author_id;
}
