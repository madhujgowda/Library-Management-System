package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.ItemStatus;
import com.github.librarymanagementsystem.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemStatusRepo extends JpaRepository<ItemStatus, Long> {
}
