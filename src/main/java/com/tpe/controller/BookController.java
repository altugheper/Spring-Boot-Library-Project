package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/query") // http://localhost:8080/books/query?id=1
    public ResponseEntity<Book> getBook(@RequestParam("id") Long id){
        Book book = bookService.findBook(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping("{id}") // http://localhost:8080/books/1
    public ResponseEntity<Book> getBookWithPath(@PathVariable("id") Long id){
        Book book = bookService.findBook(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}") // http://localhost:8080/books/1 + DELETE
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable("id") Long id){

        bookService.deleteBook(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Book is deleted succesfully");
        map.put("status", "true");

        return new  ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PutMapping("{id}") // http://localhost:8080/books/1 + PUT
    public ResponseEntity<Map<String,String>> updateBook(@PathVariable("id") Long id, @Valid
                                                         @RequestBody BookDTO bookDTO){
        bookService.updateBook(id,bookDTO);

        Map<String,String> map = new HashMap<>();
        map.put("message","Book is updated successfully");
        map.put("status", "true");

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Book>> getAllWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam("direction")Sort.Direction direction){

        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Book> bookPage = bookService.getAllWithPage(pageable);
        return ResponseEntity.ok(bookPage);
    }

    // 4





}
