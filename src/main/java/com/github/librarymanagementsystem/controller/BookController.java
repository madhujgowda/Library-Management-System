package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.service.interfaces.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
