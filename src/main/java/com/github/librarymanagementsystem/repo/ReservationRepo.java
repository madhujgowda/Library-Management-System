package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
}
