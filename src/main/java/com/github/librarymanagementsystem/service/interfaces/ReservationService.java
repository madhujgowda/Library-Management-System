package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.entity.Reservation;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> listAllReservations();
    List<ReservationDTO> getReservationById(Long reservationId);

    List<ReservationDTO> getReservationByUserId(Long userId);

    List<ReservationDTO> getReservationByItemId(Long itemId);

    Reservation createReservation(Reservation reservation);

    String deleteReservation(Long reservationId);
}
