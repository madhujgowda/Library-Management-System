package com.github.librarymanagementsystem.entity;

import jakarta.persistence.*;

@Entity(name = "user_type")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "max_checkout")
    private int maxCheckout;

    @Column(name = "max_reservation")
    private int maxReservation;

    @Column(name = "max_renewal")
    private int maxRenewal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxCheckout() {
        return maxCheckout;
    }

    public void setMaxCheckout(int maxCheckout) {
        this.maxCheckout = maxCheckout;
    }

    public int getMaxReservation() {
        return maxReservation;
    }

    public void setMaxReservation(int maxReservation) {
        this.maxReservation = maxReservation;
    }

    public int getMaxRenewal() {
        return maxRenewal;
    }

    public void setMaxRenewal(int maxRenewal) {
        this.maxRenewal = maxRenewal;
    }
}
