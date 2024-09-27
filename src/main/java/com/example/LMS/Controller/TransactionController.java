package com.example.LMS.Controller;

import com.example.LMS.Model.Book;
import com.example.LMS.Service.BookService;
import com.example.LMS.Service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BookService bookService;

    @PutMapping("issueBookToLibraryCard")
    public ResponseEntity<String> issueBookToLibraryCard(@RequestParam("LibraryCardId") Integer LibraryCardId , @RequestParam("bookId") Integer bookId){
        try {
            String result = transactionService.issueBookToLibraryCard(LibraryCardId , bookId);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Could not issue book to LibraryCard.");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookTransactionStatus")
    public ResponseEntity<List<Book>> bookTransactionStatus(){
        try {
            List<Book> bookList = transactionService.bookTransactionStatus();
            return new ResponseEntity<>(bookList , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to fetch the book list transaction status.");
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam("LibraryCardId") Integer LibraryCardId , @RequestParam("bookId") Integer bookId){
        try {
            String result = transactionService.returnBook(LibraryCardId , bookId);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to return the book.");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/totalFine")
    public ResponseEntity<String> totalFine(){
        try {
            String result = transactionService.totalFine();
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Unable to fetch the total fine Amount.");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }
}
