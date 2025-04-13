package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Game;
import com.github.librarymanagementsystem.entity.Item;

import java.util.List;

public class GameDTO extends Game {
    List<Item> items;

    int noOfCopies;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
}
