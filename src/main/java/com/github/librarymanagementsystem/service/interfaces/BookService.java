package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.entity.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> listAllBooks();

    Book getBookById(Long bookId);

    Book addBook(BookDTO bookDTO);

    Book updateBook(Book book);
}
