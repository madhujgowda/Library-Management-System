package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Checkout;
import com.github.librarymanagementsystem.entity.User;

public class CheckoutDTO extends Checkout {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
