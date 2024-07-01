package com.library.programmingexercise.mapper;

import com.library.programmingexercise.dto.AdminDto;
import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.Admin;
import com.library.programmingexercise.entity.Book;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BookMapper {
    public static BookDto maptoBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setAuthorName(book.getAuthorName());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setTitle(book.getTitle());
        bookDto.setEdition(book.getEdition());
        bookDto.setGenre(book.getGenre());
        bookDto.setQuantity(book.getQuantity());
        bookDto.setAvailable(book.getAvailable());
        bookDto.setIssued(book.getIssued());
        bookDto.setYearOfPublication(book.getYearOfPublication());

        // Convert byte array image to Base64
        byte[] imageBytes = book.getImage();
        if (imageBytes != null) {
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            bookDto.setImage(base64Image);
        }

        return bookDto;
    }

    public static List<BookDto> mapToListBookDto(List<Book> books){
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(maptoBookDto(book));
        }
        return bookDtos;
    }

    public static Book maptoBook(BookDto bookDto) {
        return new Book(
                bookDto.getId(),
                bookDto.getIsbn(),
                bookDto.getAuthorName(),
                bookDto.getPublisher(),
                bookDto.getTitle(),
                Base64.getDecoder().decode(bookDto.getImage().getBytes(StandardCharsets.UTF_8)),
                bookDto.getEdition(),
                bookDto.getGenre(),
                bookDto.getQuantity(),
                bookDto.getAvailable(),
                bookDto.getIssued(),
                bookDto.getYearOfPublication()
        );
    }
}
