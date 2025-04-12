package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
