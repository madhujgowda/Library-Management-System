package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.entity.Reservation;
import com.github.librarymanagementsystem.service.interfaces.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ResponseBody
    @RequestMapping("/user/{userId}")
    public List<ReservationDTO> getReservationByUserId(@PathVariable("userId") Long userId) {
        return reservationService.getReservationByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Reservation createReservation(@RequestBody Reservation reservation) {
        if (reservation == null) {
            throw new IllegalStateException("Please submit a reservation to create.");
        }

        return reservationService.createReservation(reservation);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{reservationId}", method = RequestMethod.DELETE)
    public String deleteReservation(@PathVariable("reservationId") Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}
