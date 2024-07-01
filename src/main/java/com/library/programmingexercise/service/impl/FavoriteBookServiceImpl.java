package com.library.programmingexercise.service.impl;

import com.library.programmingexercise.dto.BookDto;
import com.library.programmingexercise.entity.Book;
import com.library.programmingexercise.entity.FavoriteBook;
import com.library.programmingexercise.exception.ResourceNotFoundException;
import com.library.programmingexercise.mapper.BookMapper;
import com.library.programmingexercise.repository.FavoriteBookRepository;
import com.library.programmingexercise.service.FavoriteBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteBookServiceImpl implements FavoriteBookService {
    @Autowired
    private FavoriteBookRepository favoriteBookRepository;

    // Add the addFavoriteBook method here
    @Override
    public void addFavoriteBook(Integer bookId, Integer userId) {
        FavoriteBook favoriteBook = new FavoriteBook();
        favoriteBook.setBookID(bookId);
        favoriteBook.setReaderID(userId);
        favoriteBookRepository.save(favoriteBook);
    }

    // Add the getAllFavoriteBooks method here
    @Override
    public List<BookDto> getAllFavoriteBooks(int userId) {
        List<Book> books = favoriteBookRepository.findAllFavoriteBooks(userId);
        return BookMapper.mapToListBookDto(books);
    }

    // Add the deleteFavoriteBook method here
    @Transactional
    public void deleteFavoriteBook(Integer bookId, Integer userId) {
        favoriteBookRepository.deleteByBookIDAndReaderID(bookId, userId);
    }
}
