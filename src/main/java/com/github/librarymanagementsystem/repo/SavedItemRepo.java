package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Reservation;
import com.github.librarymanagementsystem.entity.SavedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedItemRepo extends JpaRepository<SavedItem, Long> {

    List<SavedItem> findByUserId(Long userId);

    List<SavedItem> findByMediaId(Long mediaId);
}
