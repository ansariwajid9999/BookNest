package com.example.LMS.Service;

import com.example.LMS.Enum.CardStatus;
import com.example.LMS.Enum.TransactionStatus;
import com.example.LMS.Enum.TransactionType;
import com.example.LMS.Model.Book;
import com.example.LMS.Model.LibraryCard;
import com.example.LMS.Model.Transaction;
import com.example.LMS.Repository.BookRepository;
import com.example.LMS.Repository.LibraryCardRepository;
import com.example.LMS.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private BookRepository bookRepository;

    private static final Integer bookLimit = 6;

    public String issueBookToLibraryCard(Integer libraryCardId, Integer bookId) throws Exception{
        Transaction transaction = new Transaction(TransactionStatus.PENDING , TransactionType.ISSUE , 0);

        Optional<LibraryCard> optional = libraryCardRepository.findById(libraryCardId);
        Optional<Book> optional1 = bookRepository.findById(bookId);

        /*
        This line will give true by default.
        System.out.println("isAvailable: " + optional1.get().getIsAvailable());
         */

        if(optional.isEmpty()){
            throw new Exception("Invalid LibraryCardId!.");
        }
        if(optional1.isEmpty()){
            throw new Exception("Invalid BookId!.");
        }
        if(optional1.get().getIsAvailable() == Boolean.FALSE){
            throw new Exception("Book not available");
        }

        LibraryCard libraryCard = optional.get();
        Book book = optional1.get();

        if(!(libraryCard.getCardStatus().equals(CardStatus.ACTIVE))){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new Exception("LibraryCard is not in right status.");
        }

        if(libraryCard.getNoOfBookIssued() >= bookLimit){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);

            throw new Exception("Already maximum limit of books are issued.");
        }


        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        book.setIsAvailable(Boolean.FALSE);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued() + 1);


        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        Transaction newTransaction = transactionRepository.save(transaction);

        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        bookRepository.save(book);
        libraryCardRepository.save(libraryCard);

        return "Transaction have been done successfully.";
    }

    public List<Book> bookTransactionStatus() throws Exception{
        /*
        You can use findAll() or you the below one both working fine.
         */
        List<Transaction> transactionList = transactionRepository.findByTransactionStatus(TransactionStatus.SUCCESS);

        if(transactionList.isEmpty()){
            throw new Exception("No list is present in transaction Database.");
        }

        List<Book> bookList = new ArrayList<>();
        for(Transaction transaction : transactionList){
            bookList.add(transaction.getBook());
        }
        return bookList;
    }

    public String returnBook(Integer LibraryCard_Id , Integer bookId) throws Exception{
        Optional<Book> optional = bookRepository.findById(bookId);
        Optional<LibraryCard> optional1 = libraryCardRepository.findById(LibraryCard_Id);

        if(optional.isEmpty()){
            throw new Exception("Book not present in Database.");
        }
        if(optional1.isEmpty()){
            throw new Exception("LibraryCard is not present in Database.");
        }

        Book book = optional.get();
        LibraryCard libraryCard = optional1.get();

        List<Transaction> transactionList = transactionRepository.findTransactionByBookAndLibraryCardAndTransactionStatusAndTransactionType(book , libraryCard , TransactionStatus.SUCCESS , TransactionType.ISSUE);

        Transaction LatestTransaction = transactionList.get(transactionList.size() - 1);
        Date issueDate = LatestTransaction.getCreatedAt();

        long TimeInMilliSecond = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        long NoOfDays = TimeUnit.DAYS.convert(TimeInMilliSecond , TimeUnit.MILLISECONDS);

        System.out.println("SystemCurrentTime: " + System.currentTimeMillis() + " issueTime: " + issueDate.getTime() + " TimeInMS: " + TimeInMilliSecond);
        System.out.println("Number of Days : " + NoOfDays);

        int fine = 0;
        if(NoOfDays > 0){
            fine = (int)((NoOfDays - 1) * 50);
        }

        System.out.println("Fine amount is: " + fine);

        book.setIsAvailable(Boolean.TRUE);
        libraryCard.setNoOfBookIssued(libraryCard.getNoOfBookIssued() - 1);

        Transaction transaction = new Transaction(TransactionStatus.SUCCESS , TransactionType.RETURN , fine);
        transaction.setBook(book);
        transaction.setLibraryCard(libraryCard);

        Transaction newTransaction = transactionRepository.save(transaction);

        book.getTransactionList().add(newTransaction);
        libraryCard.getTransactionList().add(newTransaction);

        bookRepository.save(book);
        libraryCardRepository.save(libraryCard);

        return "book has been successfully returned.";
    }

    public String totalFine() throws Exception{
        List<Transaction> transactionList = transactionRepository.findByTransactionType(TransactionType.RETURN);

        if(transactionList.isEmpty()){
            throw new Exception("No transaction has transaction type RETURN so , fineAmount is 0.");
        }
        int fineAmount = 0;
        for(Transaction transaction : transactionList){
            fineAmount += transaction.getFineAmount();
        }

        return "Total fine Amount is: " + fineAmount;
    }
}
