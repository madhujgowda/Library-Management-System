package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.entity.Genre;
import com.github.librarymanagementsystem.service.interfaces.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private GenreService genreService;

    public GenreController (GenreService genreService) {
        this.genreService = genreService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Genre> listAllGenres() {
        return genreService.listAllGenres();
    }

    @ResponseBody
    @RequestMapping("/view/{genreId}")
    public Genre getGenreById(@PathVariable("genreId") Long genreId) {
        return genreService.getGenreById(genreId);
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Genre addGenre(@RequestBody Genre genre) {
        if (genre == null) {
            throw new IllegalStateException("Please submit a genre to add.");
        }

        return genreService.addGenre(genre);
    }

    @ResponseBody
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Genre updateGenre(@RequestBody Genre genre) {
        if (genre == null) {
            throw new IllegalStateException("Please submit a genre to update.");
        }

        return genreService.updateGenre(genre);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{genreId}", method = RequestMethod.DELETE)
    public String deleteGenre(@PathVariable("genreId") Long genreId) {
        return genreService.deleteGenre(genreId);
    }

    @ResponseBody
    @RequestMapping(path = "/count", method = RequestMethod.GET)
    public long countAllGenres() {
        return genreService.countAllGenres();
    }
}
