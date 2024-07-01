package com.library.programmingexercise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "bookid", nullable = false)
    private Integer id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "author_name", nullable = false, length = 1000)
    private String authorName;

    @Column(name = "publisher", nullable = false, length = 1000)
    private String publisher;

    @Column(name = "title", nullable = false, length = 1000)
    private String title;

    @Column(name = "image", nullable = false)
    private byte[] image;

    @Column(name = "edition", nullable = false, length = 1000)
    private String edition;

    @Column(name = "genre", nullable = false, length = 1000)
    private String genre;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "available", nullable = false)
    private Integer available;

    @Column(name = "issued", nullable = false)
    private Integer issued;

    @Column(name = "year_of_publication", nullable = false)
    private Integer yearOfPublication;
}