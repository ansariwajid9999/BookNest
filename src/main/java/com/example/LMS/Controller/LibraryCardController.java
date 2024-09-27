package com.example.LMS.Controller;

import com.example.LMS.Enum.CardStatus;
import com.example.LMS.Enum.Department;
import com.example.LMS.Model.LibraryCard;
import com.example.LMS.Service.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/LibraryCard")
public class LibraryCardController {
    @Autowired
    private LibraryCardService libraryCardService;

    @PostMapping("/addLibraryCard")
    public ResponseEntity<String> addLibraryCard(@RequestBody LibraryCard libraryCard){
        try {
            String result = libraryCardService.addLibraryCard(libraryCard);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Could not add LibraryCard: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/CardNotActive")
    public ResponseEntity<List<LibraryCard>> CardNotActive(){
        try {
            List<LibraryCard> libraryCardList = libraryCardService.CardNotActive();
            return new ResponseEntity<>(libraryCardList , HttpStatus.OK);
        }
        catch (Exception e){
            log.error("All LibraryCard is ACTIVE: " + e.getMessage());
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/cardIssueToStudent")
    public ResponseEntity<String> cardIssueToStudent(@RequestParam("LibraryCard_Id") Integer LibraryCardId , @RequestParam("rollNo") Integer rollNo){
        try {
            String result = libraryCardService.cardIssueToStudent(LibraryCardId , rollNo);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("cardIssue failed!");
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCardStatusByLibraryCardId")
    public ResponseEntity<CardStatus> getCardStatusByStudentId(@RequestParam("LibraryCard_Id") Integer LibraryCardId){
        try {
            CardStatus cardStatus = libraryCardService.getCardStatusByStudentId(LibraryCardId);
            return new ResponseEntity<>(cardStatus , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Could not fetch the status from the Database.");
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("studentsCardNotActive")
    public ResponseEntity<List<String>> studentsCardNotActive(){
        try {
            List<String> result = libraryCardService.studentsCardNotActive();
            return new ResponseEntity<>(result , HttpStatus.OK);
        }
        catch (Exception e) {
            log.error("Could not fetch the list of students from Database.");
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
        }
    }
}
