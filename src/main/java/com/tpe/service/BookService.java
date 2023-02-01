package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.exception.ConflictException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void createBook(Book book) {
        if (bookRepository.existsByBookName(book.getBookName())){
            throw new ConflictException("Book already exists");
        }
        bookRepository.save(book);
    }
}
