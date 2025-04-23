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
    private String maxReservation;

    @Column(name = "max_renewal")
    private String maxRenewal;

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

    public String getMaxReservation() {
        return maxReservation;
    }

    public void setMaxReservation(String maxReservation) {
        this.maxReservation = maxReservation;
    }

    public String getMaxRenewal() {
        return maxRenewal;
    }

    public void setMaxRenewal(String maxRenewal) {
        this.maxRenewal = maxRenewal;
    }
}
