package com.github.librarymanagementsystem.mapper;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Movie;
import org.springframework.stereotype.Component;


@Component
public class MovieMapper {

    public MovieDTO mapMovieDetails(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setCost(movie.getCost());
        movieDTO.setGenre(movie.getGenre());
        movieDTO.setPublisher(movie.getPublisher());

        return movieDTO;
    }

    public Movie mapMovieDTODetails(MovieDTO movieDTO) {
        Movie movie = new Movie();

        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setCost(movieDTO.getCost());
        movie.setGenre(movieDTO.getGenre());
        movie.setPublisher(movieDTO.getPublisher());

        return movie;
    }
}
