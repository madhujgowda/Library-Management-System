package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}
