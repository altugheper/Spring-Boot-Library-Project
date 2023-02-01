package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController { // http://localhost:8080/books

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        List<Book> books = bookService.getAll();

        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> createBook(@Valid @RequestBody Book book) {
        bookService.createBook(book);
        Map<String, String> map = new HashMap<>();
        map.put("message","Book is created successfully");
        map.put("status","true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    // 2. --> 02:01


}
