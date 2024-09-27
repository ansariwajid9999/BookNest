package com.example.LMS.Service;

import com.example.LMS.Model.Author;
import com.example.LMS.Model.Book;
import com.example.LMS.Repository.AuthorRepository;
import com.example.LMS.RequestDto.AuthorAddRequestDto;
import com.example.LMS.RequestDto.UpdateNamePenNameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public String addAuthor(Author author) throws Exception{
        if(author.getAuthorId() != null){
            throw new Exception("Author already exists.");
        }

        authorRepository.save(author);
        return "Author has been successfully inserted into Database.";
    }

    public String UpdateNamePenName(UpdateNamePenNameDto updateNamePenNameDto) throws Exception{
        Optional<Author> optional = authorRepository.findById(updateNamePenNameDto.getAuthor_id());

        if(optional.isEmpty()){
            throw new Exception("Invalid Author_id");
        }

        Author author = optional.get();
        author.setName(updateNamePenNameDto.getNewName());
        author.setPenName(updateNamePenNameDto.getNewPenName());

        authorRepository.save(author);

        return "Author has been successfully update into Database.";
    }

    public Author getAuthorById(Integer authorId) throws Exception{
        Optional<Author> optional = authorRepository.findById(authorId);

        if(optional.isEmpty()){
            throw new Exception("Invalid Author_id , no author present in Database with the given Author_id");
        }

        Author author = optional.get();
        authorRepository.save(author);

        return author;
    }

    public List<Book> getBookListById(Integer authorId) throws Exception{
        Optional<Author> optional = authorRepository.findById(authorId);

        if(optional.isEmpty()){
            throw new Exception("Invalid Author_id , no author present in Database with the given Author_id");
        }

        Author author = optional.get();
        List<Book> bookList = author.getBookList();

        return bookList;
    }
}
