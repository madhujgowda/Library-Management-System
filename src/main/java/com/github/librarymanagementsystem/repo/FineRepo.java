package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Checkout;
import com.github.librarymanagementsystem.entity.Fine;
import com.github.librarymanagementsystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepo extends JpaRepository<Fine, Long> {

    List<Fine> findByUserId(Long userId);
}
