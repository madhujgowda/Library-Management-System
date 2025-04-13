package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Movie;
import com.github.librarymanagementsystem.service.interfaces.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private MovieService movieService;

    public MovieController (MovieService movieService) {
        this.movieService = movieService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<MovieDTO> listAllMovies() {
        return movieService.listAllMovies();
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Movie addMovie(@RequestBody MovieDTO movieDTO) {
        if (movieDTO == null) {
            throw new IllegalStateException("Please submit a movei to add.");
        }

        return movieService.addMovie(movieDTO);
    }
}
