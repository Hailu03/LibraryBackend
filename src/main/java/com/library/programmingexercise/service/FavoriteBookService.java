package com.library.programmingexercise.service;

import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

// an interface for the FavoriteBookService
public interface FavoriteBookService {
    void addFavoriteBook(Integer bookId, Integer userId);

    List<BookDto> getAllFavoriteBooks(int userId);

    void deleteFavoriteBook(Integer bookId, Integer userId);
}
