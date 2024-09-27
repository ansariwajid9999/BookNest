package com.example.LMS.Service;

import com.example.LMS.Enum.CardStatus;
import com.example.LMS.Model.LibraryCard;
import com.example.LMS.Model.Student;
import com.example.LMS.Repository.LibraryCardRepository;
import com.example.LMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryCardService {
    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private StudentRepository studentRepository;

    public String addLibraryCard(LibraryCard libraryCard) throws Exception{
        if(libraryCard.getLibraryCardId() != null){
            throw new Exception("LibraryCard Already Exist");
        }

        libraryCardRepository.save(libraryCard);
        return "Card has been successfully added to Database.";
    }

    public List<LibraryCard> CardNotActive() throws Exception{
        Optional<List<LibraryCard>> optionalLibraryCardList = libraryCardRepository.findByCardStatusIn(
                Arrays.asList("DEACTIVATED", "NEW", "ISSUED", "BLOCKED")
        );

        if(optionalLibraryCardList.isEmpty()){
            throw new Exception("No library cards with the NOT ACTIVE status found in Database.");
        }

        List<LibraryCard> libraryCardList = optionalLibraryCardList.get();
        return libraryCardList;
    }

    public String cardIssueToStudent(Integer libraryCardId, Integer rollNo) throws Exception{
        Optional<LibraryCard> optional = libraryCardRepository.findById(libraryCardId);
        Optional<Student> optional1 = studentRepository.findById(rollNo);

        if(optional.isEmpty()){
            throw new Exception("LibraryCard_Id is invalid!.");
        }
        if(optional1.isEmpty()){
            throw new Exception("rollNo is invalid!.");
        }

        LibraryCard libraryCard = optional.get();
        Student student = optional1.get();

        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);

        studentRepository.save(student);

        return "card successfully issued to given rollNo student.";
    }

    public CardStatus getCardStatusByStudentId(Integer libraryCardId) throws Exception{
        Optional<LibraryCard> optional = libraryCardRepository.findById(libraryCardId);

        if(optional.isEmpty()){
            throw new Exception("LibraryCard_Id is invalid!.");
        }

        LibraryCard libraryCard = optional.get();
        CardStatus cardStatus = libraryCard.getCardStatus();

        return cardStatus;
    }

    /*
    Make sure that you have issued Library card to every student , if any card has student_id null it will fail.
     */
    public List<String> studentsCardNotActive() throws Exception{
        Optional<List<LibraryCard>> optionalLibraryCardList = libraryCardRepository.findByCardStatusIn(
                Arrays.asList("DEACTIVATED", "NEW", "ISSUED", "BLOCKED")
        );

        if(optionalLibraryCardList.isEmpty()){
            throw new Exception("No library cards with the NOT ACTIVE status found in Database.");
        }

        List<String> list = new ArrayList<>();
        for(LibraryCard libraryCard : optionalLibraryCardList.get()){

            Student student = libraryCard.getStudent();
            list.add(student.getName());
        }

        return list;
    }
}
