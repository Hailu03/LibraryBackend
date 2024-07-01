package com.library.programmingexercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Integer id;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("authorName")
    private String authorName;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("title")
    private String title;

    @JsonProperty("image")
    private String image;

    @JsonProperty("edition")
    private String edition;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("available")
    private Integer available;

    @JsonProperty("issued")
    private Integer issued;

    @JsonProperty("yearOfPublication")
    private Integer yearOfPublication;
}
