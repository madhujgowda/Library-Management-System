package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.Movie;
import com.github.librarymanagementsystem.mapper.BookMapper;
import com.github.librarymanagementsystem.mapper.MovieMapper;
import com.github.librarymanagementsystem.repo.BookRepo;
import com.github.librarymanagementsystem.repo.ItemRepo;
import com.github.librarymanagementsystem.repo.MovieRepo;
import com.github.librarymanagementsystem.service.interfaces.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepo movieRepo;

    private MovieMapper movieMapper;

    private ItemRepo itemRepo;

    public MovieServiceImpl (MovieRepo movieRepo, MovieMapper movieMapper, ItemRepo itemRepo) {
        this.movieRepo = movieRepo;
        this.movieMapper = movieMapper;
        this.itemRepo = itemRepo;
    }
    @Override
    public List<MovieDTO> listAllMovies() {
        List<Movie> movieList = movieRepo.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for (Movie movie: movieList) {
            MovieDTO movieDTO = movieMapper.mapMovieDetails(movie);

            List<Item> itemList = itemRepo.findByItemTypeIdAndMediaId(1L, movie.getId());

            movieDTO.setItems(itemList);

            movieDTOList.add(movieDTO);
        }

        return movieDTOList;
    }
}
