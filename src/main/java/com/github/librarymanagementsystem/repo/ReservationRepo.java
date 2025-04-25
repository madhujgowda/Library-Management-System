package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByItemId(Long userId);

}
