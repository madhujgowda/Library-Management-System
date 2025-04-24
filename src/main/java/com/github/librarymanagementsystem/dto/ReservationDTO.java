package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Reservation;

public class ReservationDTO extends Reservation {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
