package com.github.librarymanagementsystem.mapper;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDTO mapReservationDetails(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId(reservation.getId());
        reservationDTO.setItem(reservation.getItem());
        reservationDTO.setUser(reservation.getUser());
        reservationDTO.setDate(reservation.getDate());

        return reservationDTO;
    }
}
