package com.example.LMS.ResponseDto;

import com.example.LMS.Enum.Genre;
import lombok.Data;

import java.util.Date;

@Data
public class BookResponseDto {
    private String title;

    private Boolean isAvailable;

    private Genre genre;

    private Date publicationDate;

    private Integer price;

    private String authorName;

    public BookResponseDto(String title, Boolean isAvailable, Genre genre, Date publicationDate, Integer price, String name) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.price = price;
        this.authorName = name;
    }
}
