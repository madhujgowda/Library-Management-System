package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemTypeRepo extends JpaRepository<ItemType, Long> {
}
