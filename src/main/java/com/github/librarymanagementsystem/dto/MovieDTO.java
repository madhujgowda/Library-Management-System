package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.Movie;

import java.util.List;

public class MovieDTO extends Movie {

    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
