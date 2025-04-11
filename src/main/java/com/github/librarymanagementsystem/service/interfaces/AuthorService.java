package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAllAuthors();

    Author getAuthorById(Long authorId);

    Author addAuthor(Author author);

    Author updateAuthor(Author author);

    String deleteAuthor(Long authorId);

    long countAllAuthors();
}
