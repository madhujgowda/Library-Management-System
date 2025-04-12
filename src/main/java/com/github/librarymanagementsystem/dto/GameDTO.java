package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Game;
import com.github.librarymanagementsystem.entity.Item;

import java.util.List;

public class GameDTO extends Game {
    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
