package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getReservationByUserId(Long userId);

    Reservation createReservation(Reservation reservation);
}
