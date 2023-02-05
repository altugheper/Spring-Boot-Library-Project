package com.tpe.dto;

import com.tpe.domain.Book;
import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class BookDTO {


    private Long id;

    @NotNull(message = "Book name cannot be null")
    @NotBlank(message = "Book name cannot be white space")
    @Size(min = 2, max = 50, message = "Book name '${validatedValue}' must be between {min} and {max} long")
    private String bookName;

    @NotNull(message = "Author name cannot be null")
    @NotBlank(message = "Author name cannot be white space")
    @Size(min = 2, max = 50, message = "Author name '${validatedValue}' must be between {min} and {max} long")
    private String authorName;


    private Integer pages;



    private LocalDateTime createDate = LocalDateTime.now();

    public BookDTO(Book book){
        this.id = book.getId();
        this.bookName = book.getBookName();
        this.authorName = book.getAuthorName();
        this.pages = book.getPages();
        this.createDate = book.getCreateDate();
    }

    // 3 --> 1:37

}
