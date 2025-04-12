package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.service.interfaces.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
