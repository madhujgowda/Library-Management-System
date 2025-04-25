package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.FineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineStatusRepo extends JpaRepository<FineStatus, Long> {
}
