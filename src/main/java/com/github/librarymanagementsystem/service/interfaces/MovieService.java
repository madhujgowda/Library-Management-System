package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Movie;

import java.util.List;

public interface MovieService {

    List<MovieDTO> listAllMovies();

    Movie getMovieById(Long movieId);

    Movie addMovie(MovieDTO movieDTO);

    Movie updateMovie(Movie movie);
}
