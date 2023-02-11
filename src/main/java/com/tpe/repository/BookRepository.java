package com.tpe.repository;

import com.tpe.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    boolean existsByBookName(String bookName);

    List<Book> findByAuthorName(String authorName);

    @Query("SELECT b FROM Book b WHERE b.pages=:")
    List<Book> findAllEqualsPages(Integer pages);
}