package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Fine;

public class FineDTO extends Fine {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
