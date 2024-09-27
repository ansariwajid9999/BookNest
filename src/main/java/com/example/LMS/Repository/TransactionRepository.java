package com.example.LMS.Repository;

import com.example.LMS.Enum.TransactionStatus;
import com.example.LMS.Enum.TransactionType;
import com.example.LMS.Model.Book;
import com.example.LMS.Model.LibraryCard;
import com.example.LMS.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction , Integer> {
    List<Transaction> findByTransactionStatus(TransactionStatus transactionStatus);
    List<Transaction> findTransactionByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book, LibraryCard libraryCard, TransactionStatus transactionStatus, TransactionType transactionType);
    List<Transaction> findByTransactionType(TransactionType transactionType);
}
