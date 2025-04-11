package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.entity.Publisher;
import com.github.librarymanagementsystem.repo.AuthorRepo;
import com.github.librarymanagementsystem.service.interfaces.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }
    @Override
    public List<Author> listAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Author getAuthorById(Long authorId) {
        Optional<Author> authorResult = authorRepo.findById(authorId);

        Author author = null;
        if (authorResult.isPresent()) {
            author = authorResult.get();
        }

        return author;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public String deleteAuthor(Long authorId) {
        Optional<Author> authorResult = authorRepo.findById(authorId);

        if (authorResult.isPresent()) {
            authorRepo.delete(authorResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to delete. Please try again");
        }
    }

    @Override
    public long countAllAuthors() {
        return authorRepo.count();
    }
}
