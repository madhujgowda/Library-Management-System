package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.entity.Author;
import com.github.librarymanagementsystem.entity.Publisher;
import com.github.librarymanagementsystem.service.interfaces.AuthorService;
import com.github.librarymanagementsystem.service.interfaces.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Author> listAllAuthors() {
        return authorService.listAllAuthors();
    }

    @ResponseBody
    @RequestMapping("/view/{authorId}")
    public Author getAuthorById(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Author addAuthor(@RequestBody Author author) {
        if (author == null) {
            throw new IllegalStateException("Please submit a author to add.");
        }

        return authorService.addAuthor(author);
    }

    @ResponseBody
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Author updateAuthor(@RequestBody Author author) {
        if (author == null) {
            throw new IllegalStateException("Please submit a author to update.");
        }

        return authorService.updateAuthor(author);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{publisherId}", method = RequestMethod.DELETE)
    public String deletePublisher(@PathVariable("publisherId") Long publisherId) {
        return authorService.deleteAuthor(publisherId);
    }

    @ResponseBody
    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public long countAllAuthors() {
        return authorService.countAllAuthors();
    }
}
