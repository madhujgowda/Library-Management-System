package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.entity.Movie;
import com.github.librarymanagementsystem.mapper.MovieMapper;
import com.github.librarymanagementsystem.repo.ItemRepo;
import com.github.librarymanagementsystem.repo.ItemTypeRepo;
import com.github.librarymanagementsystem.repo.MovieRepo;
import com.github.librarymanagementsystem.service.interfaces.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepo movieRepo;

    private MovieMapper movieMapper;

    private ItemRepo itemRepo;

    private ItemTypeRepo itemTypeRepo;

    public MovieServiceImpl (MovieRepo movieRepo, MovieMapper movieMapper, ItemRepo itemRepo, ItemTypeRepo itemTypeRepo) {
        this.movieRepo = movieRepo;
        this.movieMapper = movieMapper;
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
    }
    @Override
    public List<MovieDTO> listAllMovies() {
        List<Movie> movieList = movieRepo.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for (Movie movie: movieList) {
            MovieDTO movieDTO = movieMapper.mapMovieDetails(movie);

            Optional<ItemType> movieItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("movie")).findFirst();

            List<Item> itemList = itemRepo.findByItemTypeIdAndMediaId(movieItemType.get().getId(), movie.getId());

            movieDTO.setItems(itemList);

            movieDTOList.add(movieDTO);
        }

        return movieDTOList;
    }
}
