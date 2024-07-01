package com.library.programmingexercise.controller;

import com.library.programmingexercise.dto.AdminDto;
import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.FavoriteBook;
import com.library.programmingexercise.service.FavoriteBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/favorite-books")
public class FavoriteBookController {
    FavoriteBookService favoriteBookService;

    // API for adding a favorite book
    @PostMapping
    public void addFavoriteBook(@RequestBody FavoriteBook favoriteBook) {
        System.out.println(favoriteBook.getBookID());
        System.out.println(favoriteBook.getReaderID());
        favoriteBookService.addFavoriteBook(favoriteBook.getBookID(), favoriteBook.getReaderID());
    }

    // API for getting all favorite books
    @GetMapping("/{id}")
    public List<BookDto> getAllFavoriteBooks(@PathVariable int id) {
        return favoriteBookService.getAllFavoriteBooks(id);
    }

    // API for deleting a favorite book
    @DeleteMapping("/{bookId}/{userId}")
    public void deleteFavoriteBook(@PathVariable Integer bookId, @PathVariable Integer userId) {
        favoriteBookService.deleteFavoriteBook(bookId, userId);
    }
}
