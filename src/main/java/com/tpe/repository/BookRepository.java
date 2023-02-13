package com.tpe.repository;

import com.tpe.domain.Book;
import com.tpe.dto.BookDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    boolean existsByBookName(String bookName);

    List<Book> findByAuthorName(String authorName);

    @Query("SELECT b FROM Book b WHERE b.pages=:pPage")
    List<Book> findAllEqualsPages(@Param("pPage") Integer pages);

    @Query("SELECT new com.tpe.dto.BookDTO(b) FROM Book b WHERE b.id=:id")
    Optional<BookDTO> findBookDTOById(@Param("id") Long id);
}