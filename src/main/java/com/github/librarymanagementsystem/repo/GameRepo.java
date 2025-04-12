package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepo extends JpaRepository<Game, Long> {
}
