package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.mapper.MovieMapper;
import com.github.librarymanagementsystem.repo.ItemRepo;
import com.github.librarymanagementsystem.repo.ItemStatusRepo;
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

    private ItemStatusRepo itemStatusRepo;

    public MovieServiceImpl (MovieRepo movieRepo, MovieMapper movieMapper, ItemRepo itemRepo, ItemTypeRepo itemTypeRepo, ItemStatusRepo itemStatusRepo) {
        this.movieRepo = movieRepo;
        this.movieMapper = movieMapper;
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
        this.itemStatusRepo = itemStatusRepo;
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

    @Override
    public Movie getMovieById(Long movieId) {
        Optional<Movie> movieResult = movieRepo.findById(movieId);

        Movie movie = null;
        if (movieResult.isPresent()) {
            movie = movieResult.get();
        }

        return movie;
    }

    @Override
    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = movieMapper.mapMovieDTODetails(movieDTO);

        movie = movieRepo.save(movie);

        Optional<ItemType> movieItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("movie")).findFirst();
        Optional<ItemStatus> availableItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Available")).findFirst();

        for (int i = 1; i <= movieDTO.getNoOfCopies(); i++) {
            Item item = new Item();
            item.setMediaId(movie.getId());
            item.setItemType(movieItemType.get());
            item.setItemStatus(availableItemStatus.get());
            itemRepo.save(item);
        }

        return movie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return movieRepo.save(movie);
    }
}
