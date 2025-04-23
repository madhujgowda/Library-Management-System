package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.service.interfaces.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    public BookController (BookService bookService) {
        this.bookService = bookService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<BookDTO> listAllBooks() {
        return bookService.listAllBooks();
    }

    @ResponseBody
    @RequestMapping("/view/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Book addBook(@RequestBody BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalStateException("Please submit a book to add.");
        }

        return bookService.addBook(bookDTO);
    }

    @ResponseBody
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Book updateBook(@RequestBody Book book) {
        if (book == null) {
            throw new IllegalStateException("Please submit a book to update.");
        }

        return bookService.updateBook(book);
    }
}
