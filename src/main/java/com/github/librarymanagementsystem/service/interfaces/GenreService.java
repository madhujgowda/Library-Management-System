package com.github.librarymanagementsystem.service.interfaces;


import com.github.librarymanagementsystem.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> listAllGenres();

    Genre getGenreById(Long genreId);

    Genre addGenre(Genre genre);

    Genre updateGenre(Genre genre);

    String deleteGenre(Long genreId);

    long countAllGenres();
}
