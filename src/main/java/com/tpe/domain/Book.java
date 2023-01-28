package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull(message = "Book name cannot be null")
    @NotBlank(message = "Book name cannot be white space")
    @Size(min = 2, max = 50, message = "Book name '${validatedValue}' must be between {min} and {max} long")
    @Column(nullable = false,length = 25)
    private String bookName;

    @NotNull(message = "Author name cannot be null")
    @NotBlank(message = "Author name cannot be white space")
    @Size(min = 2, max = 50, message = "Author name '${validatedValue}' must be between {min} and {max} long")
    @Column(nullable = false,length = 25)
    private String authorName;

    @Setter(AccessLevel.NONE)
    private LocalDateTime createDate = LocalDateTime.now();
}
