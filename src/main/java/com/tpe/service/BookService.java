package com.tpe.service;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Book findBook(Long id) {
       return bookRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Book is not found" + id));

    }

    public void deleteBook(Long id) {
        Book book = findBook(id);
        bookRepository.delete(book);
    }

    public void updateBook(Long id, BookDTO bookDTO) {
        Book book = findBook(id);
        book.setBookName(bookDTO.getBookName());
        book.setAuthorName(bookDTO.getAuthorName());
        book.setPages(bookDTO.getPages());

        bookRepository.save(book);
    }

    public Page<Book> getAllWithPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }


    public List<Book> findBook(String authorName){
        return bookRepository.findByAuthorName(authorName);
    }

    public List<Book> findAllEqualsPages(Integer pages) {
        return bookRepository.findAllEqualsPages(pages);
    }
}
