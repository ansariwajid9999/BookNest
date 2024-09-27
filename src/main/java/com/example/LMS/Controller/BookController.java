package com.example.LMS.Controller;

import com.example.LMS.Enum.Genre;
import com.example.LMS.Model.Book;
import com.example.LMS.RequestDto.BookAddRequestDto;
import com.example.LMS.ResponseDto.BookResponseDto;
import com.example.LMS.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity addBook(@RequestBody BookAddRequestDto bookAddRequestDto){
        try{
            String result = bookService.addBook(bookAddRequestDto);
            return new ResponseEntity(result , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Book could not been added to database: " + e.getMessage());
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookByGenre/{Genre}")
    // you can also send via @RequestParam ("Genre") Genre genre
    public ResponseEntity<BookResponseDto> getBookByGenre(@PathVariable("Genre") Genre genre){
        try {
            BookResponseDto bookResponseDto = bookService.getBookByGenre(genre);
            return new ResponseEntity<>(bookResponseDto , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Could not fetch the Genre: " + e.getMessage());
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookListByGenre")
    // you cannot find book by Genre because 1 genre can have multiple books which return list of book
    public ResponseEntity<List<BookResponseDto>> getBookListByGenre(@RequestParam("Genre") Genre genre){
        try {
            List<BookResponseDto> bookResponseDtoList = bookService.getBookListByGenre(genre);
            return new ResponseEntity<>(bookResponseDtoList , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Could not fetch the Genre: " + e.getMessage());
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
        }
    }
}
