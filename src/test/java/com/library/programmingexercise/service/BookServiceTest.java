package com.library.programmingexercise.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import com.library.programmingexercise.dto.BookDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void getAllBooks() {
        // Ensure that the bookService is injected by Spring
        assertNotNull(bookService, "BookService is null. Check Spring configuration.");

        // Test the getAllBooks method
        List<BookDto> books = bookService.getAllBooks();
        int size = books.size();
        assertEquals(1001, size, "Expected 1001 books, but found " + size);
    }
}
