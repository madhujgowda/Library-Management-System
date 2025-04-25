package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.SavedItem;

public class SavedItemDTO extends SavedItem {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
