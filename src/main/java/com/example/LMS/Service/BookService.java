package com.example.LMS.Service;

import com.example.LMS.Enum.Genre;
import com.example.LMS.Enum.TransactionStatus;
import com.example.LMS.Model.Author;
import com.example.LMS.Model.Book;
import com.example.LMS.Model.Transaction;
import com.example.LMS.Repository.AuthorRepository;
import com.example.LMS.Repository.BookRepository;
import com.example.LMS.Repository.TransactionRepository;
import com.example.LMS.RequestDto.BookAddRequestDto;
import com.example.LMS.ResponseDto.BookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public String addBook(BookAddRequestDto bookAddRequestDto) throws Exception{
        // Validation --> Author_id should be valid
        Optional<Author> optional = authorRepository.findById(bookAddRequestDto.getAuthor_id());

        if(optional.isEmpty()){
            throw new Exception("Invalid Author_id , Already present in Database.");
        }

        Author author = optional.get();

        Book book = new Book(bookAddRequestDto.getTitle() , bookAddRequestDto.getIsAvailable() , bookAddRequestDto .getGenre() , bookAddRequestDto.getPublicationDate() , bookAddRequestDto.getPrice());


        // Since , it's a bidirectional mapping so , we need to set both in parent and child class
        book.setAuthor(author);

        // setting in parent
        List<Book> list = author.getBookList();
        list.add(book);
        author.setBookList(list);

        // Now I need to save them
        // Save only the parent : child will get automatically saved

        authorRepository.save(author);

        return "Book has been successfully added in Database.";
    }

    public BookResponseDto getBookByGenre(Genre genre) throws Exception{
        Optional<Book> optional = bookRepository.findBookByGenre(genre);

        if(optional.isEmpty()){
            throw new Exception("genre is not present in Database.");
        }

        Book book = optional.get();

        BookResponseDto bookResponseDto = new BookResponseDto(book.getTitle() , book.getIsAvailable() , book.getGenre()
                                        , book.getPublicationDate() , book.getPrice() , book.getAuthor().getName());

        return bookResponseDto;

        // you can also do like that change the return type to Book in service and controller class
        // Book book = optional.get();
        // return book;
    }

    public List<BookResponseDto> getBookListByGenre(Genre genre) throws Exception{
        Optional<List<Book>> optionalBookList = bookRepository.findBookListByGenre(genre);

        if(optionalBookList.isEmpty()){
            throw new Exception("genre is not present in Database.");
        }
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for(Book book : optionalBookList.get()){

            BookResponseDto bookResponseDto = new BookResponseDto(book.getTitle() , book.getIsAvailable() , book.getGenre()
                    , book.getPublicationDate() , book.getPrice() , book.getAuthor().getName());

            bookResponseDtoList.add(bookResponseDto);
        }
        return bookResponseDtoList;
    }
}
