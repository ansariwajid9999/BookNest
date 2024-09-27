package com.example.LMS.Model;

import com.example.LMS.Enum.TransactionStatus;
import com.example.LMS.Enum.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    private Integer fineAmount;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private LibraryCard libraryCard;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Book book;

    public Transaction(){

    }
    public Transaction(TransactionStatus transactionStatus, TransactionType transactionType, int i) {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.fineAmount = i;
    }
}
