package com.example.LMS.Controller;

import com.example.LMS.Model.Author;
import com.example.LMS.Model.Book;
import com.example.LMS.RequestDto.AuthorAddRequestDto;
import com.example.LMS.RequestDto.UpdateNamePenNameDto;
import com.example.LMS.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        try {
            String result = authorService.addAuthor(author);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Author could not be added in Database: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/UpdateNamePenName")
    public ResponseEntity<String> UpdateNamePenName(@RequestBody UpdateNamePenNameDto updateNamePenNameDto){
        try{
            String result = authorService.UpdateNamePenName(updateNamePenNameDto);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Could not update in Database: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAuthorById")
    public ResponseEntity<Author> getAuthorById(@RequestParam("author_id") Integer author_id){
        try{
            Author author = authorService.getAuthorById(author_id);
            return new ResponseEntity<>(author , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Could not fetch the Author: " + e.getMessage());
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getBookListById")
    public ResponseEntity<List<Book>> getBookListById(@RequestParam("author_id") Integer author_id){
        try {
            List<Book> bookList = authorService.getBookListById(author_id);
            return new ResponseEntity<>(bookList , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Could not fetch the book: " + e.getMessage());
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
}
