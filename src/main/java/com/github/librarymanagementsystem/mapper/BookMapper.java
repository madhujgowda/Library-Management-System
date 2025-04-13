package com.github.librarymanagementsystem.mapper;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO mapBookDetails(Book book) {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setCost(book.getCost());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setPublisher(book.getPublisher());

        return bookDTO;
    }

    public Book mapBookDTODetails(BookDTO bookDTO) {
        Book book = new Book();

        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setCost(bookDTO.getCost());
        book.setAuthor(bookDTO.getAuthor());
        book.setGenre(bookDTO.getGenre());
        book.setPublisher(bookDTO.getPublisher());

        return book;
    }
}
