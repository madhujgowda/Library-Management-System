package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Reservation;
import com.github.librarymanagementsystem.entity.SavedItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedItemRepo extends JpaRepository<SavedItem, Long> {
}
