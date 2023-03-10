package com.tpe.controller;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import com.tpe.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController { // http://localhost:8080/books

    Logger logger = LoggerFactory.getLogger(BookController.class);

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
            @RequestParam(value = "direction", required = false, defaultValue = "DESC")Sort.Direction direction){

        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,prop));
        Page<Book> bookPage = bookService.getAllWithPage(pageable);
        return ResponseEntity.ok(bookPage);
    }

    @GetMapping("/queryauthorname") // http://localhost:8080/books/queryauthorname
    public ResponseEntity<List<Book>> getBookByAuthorName(@RequestParam("authorName")String authorName){
        List<Book> list = bookService.findBook(authorName);

        return ResponseEntity.ok(list);
    }


    @GetMapping("/pages/{pages}") // http://localhost:8080/books/pages/1101
    public ResponseEntity<List<Book>> getBooksEqualsPages(@PathVariable("pages") Integer pages){
        List<Book> list = bookService.findAllEqualsPages(pages);

        return ResponseEntity.ok(list);
    }

    // 5 --> 1:04

    // Getting data from DB as DTO
    @GetMapping("query/dto") // http://localhost:8080/books/query/dto?id=1
    public ResponseEntity<BookDTO> getBookDTO(@RequestParam("id") Long id){
        BookDTO bookDTO = bookService.findBookDTOById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/welcome") // http://localhost:8080/books/welcome
    public String welcome(HttpServletRequest request){
        logger.warn("---------------Welcome {}", request.getServletPath());
        return "Welcome to Student Controller";
    }



}
