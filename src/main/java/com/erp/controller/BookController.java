package com.erp.controller;

import com.erp.dto.BookDto;
import com.erp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.createBook(bookDto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable long id) {
        Optional<BookDto> bookDto = bookService.getBookById(id);
        return bookDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable long id, @RequestBody BookDto bookDto) {
        Optional<BookDto> updatedBook = bookService.updateBook(id, bookDto);
        return updatedBook.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    // Get Books by Author
    @GetMapping("/author/{authorName}")
    public List<BookDto> getBooksByAuthor(@PathVariable String authorName) {
        return bookService.getBooksByAuthor(authorName);
    }

    // Get Books by Genre
    @GetMapping("/genre/{genre}")
    public List<BookDto> getBooksByGenre(@PathVariable String genre) {
        return bookService.getBooksByGenre(genre);
    }

    // Get Available Books
    @GetMapping("/available")
    public List<BookDto> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    // Calculate Total Revenue
    @GetMapping("/revenue")
    public long calculateTotalRevenue() {
        return bookService.calculateTotalRevenue();
    }

    // Get Books by Price Range
    @GetMapping("/price-range")
    public List<BookDto> getBooksByPriceRange(@RequestParam long minPrice, @RequestParam long maxPrice) {
        return bookService.getBooksByPriceRange(minPrice, maxPrice);
    }

    // Get books by salesperson
    @GetMapping("/salesperson/{salesperson}")
    public ResponseEntity<List<BookDto>> getBooksBySalesperson(@PathVariable String salesperson) {
        List<BookDto> books = bookService.getBooksBySalesperson(salesperson);
        return ResponseEntity.ok(books);
    }
}
