package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Checkout;
import com.github.librarymanagementsystem.entity.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepo extends JpaRepository<Fine, Long> {
}
