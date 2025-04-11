package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Genre;
import com.github.librarymanagementsystem.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepo extends JpaRepository<Genre, Long> {
}
