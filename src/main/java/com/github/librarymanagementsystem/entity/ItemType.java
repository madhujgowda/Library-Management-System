package com.github.librarymanagementsystem.entity;

import com.sun.jdi.FloatType;
import jakarta.persistence.*;

@Entity(name = "item_type")
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_type_id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "late_fee")
    private Float lateFee;

    @Column(name = "max_due_days")
    private int maxDueDays;

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

    public Float getLateFee() {
        return lateFee;
    }

    public void setLateFee(Float lateFee) {
        this.lateFee = lateFee;
    }

    public int getMaxDueDays() {
        return maxDueDays;
    }

    public void setMaxDueDays(int maxDueDays) {
        this.maxDueDays = maxDueDays;
    }
}
