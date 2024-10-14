package com.erp.service;

import com.erp.dto.BookDto;
import com.erp.entity.Book;
import com.erp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Convert BookDto to Book entity
    private Book convertToEntity(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .bookname(bookDto.getBookname())
                .authorname(bookDto.getAuthorname())
                .bookprice(bookDto.getBookprice())
                .bookgenre(bookDto.getBookgenre())
                .avalability(bookDto.getAvailability())
                .avalabilitycount(bookDto.getAvailabilitycount())
                .discountprice(bookDto.getDiscountprice())
                .custname(bookDto.getCustname())
                .custemail(bookDto.getCustemail())
                .custphoneNumber(bookDto.getCustphoneNumber())
                .bookspurchased(bookDto.getBooksPurchased())
                .salesperson(bookDto.getSalesperson())
                .build();
    }

    // Convert Book entity to BookDto
    private BookDto convertToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .bookname(book.getBookname())
                .authorname(book.getAuthorname())
                .bookprice(book.getBookprice())
                .bookgenre(book.getBookgenre())
                .availability(book.getAvalability())
                .availabilitycount(book.getAvalabilitycount())
                .discountprice(book.getDiscountprice())
                .custname(book.getCustname())
                .custemail(book.getCustemail())
                .custphoneNumber(book.getCustphoneNumber())
                .booksPurchased(book.getBookspurchased())
                .salesperson(book.getSalesperson())
                .build();
    }

    // Create a new book
    public BookDto createBook(BookDto bookDto) {
        Book book = convertToEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }

    // Get a book by ID
    public Optional<BookDto> getBookById(long id) {
        return bookRepository.findById(id).map(this::convertToDto);
    }

    // Get all books
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Update a book
    public Optional<BookDto> updateBook(long id, BookDto bookDto) {
        return bookRepository.findById(id).map(existingBook -> {
            Book updatedBook = convertToEntity(bookDto);
            updatedBook.setId(existingBook.getId()); // Ensure the ID remains the same
            Book savedBook = bookRepository.save(updatedBook);
            return convertToDto(savedBook);
        });
    }

    // Delete a book
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    // Get Books by Author
    public List<BookDto> getBooksByAuthor(String authorName) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAuthorname().equalsIgnoreCase(authorName))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get Books by Genre
    public List<BookDto> getBooksByGenre(String genre) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getBookgenre().equalsIgnoreCase(genre))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get Available Books
    public List<BookDto> getAvailableBooks() {
        return bookRepository.findAll().stream()
                .filter(book -> book.getAvalability().equalsIgnoreCase("Available"))
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Calculate Total Revenue from Book Sales
    public long calculateTotalRevenue() {
        return bookRepository.findAll().stream()
                .mapToLong(book -> book.getBookprice() * book.getBookspurchased())
                .sum();
    }

    // Filter Books by Price Range
    public List<BookDto> getBooksByPriceRange(long minPrice, long maxPrice) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getBookprice() >= minPrice && book.getBookprice() <= maxPrice)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //get all books by sales person
    public List<BookDto> getBooksBySalesperson(String salesperson) {
        return bookRepository.findBySalesperson(salesperson).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
