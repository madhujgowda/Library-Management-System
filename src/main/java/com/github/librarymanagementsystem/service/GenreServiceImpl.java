package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.entity.Genre;
import com.github.librarymanagementsystem.repo.GenreRepo;
import com.github.librarymanagementsystem.service.interfaces.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepo genreRepo;
    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }
    @Override
    public List<Genre> listAllGenres() {
        return genreRepo.findAll();
    }

    @Override
    public Genre getGenreById(Long genreId) {
        Optional<Genre> genreResult = genreRepo.findById(genreId);

        Genre genre = null;
        if (genreResult.isPresent()) {
            genre = genreResult.get();
        }

        return genre;
    }

    @Override
    public Genre addGenre(Genre genre) {
        return genreRepo.save(genre);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreRepo.save(genre);
    }

    @Override
    public String deleteGenre(Long genreId) {
        Optional<Genre> genreResult = genreRepo.findById(genreId);

        if (genreResult.isPresent()) {
            genreRepo.delete(genreResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to delete. Please try again");
        }
    }

    @Override
    public long countAllGenres() {
        return genreRepo.count();
    }
}
