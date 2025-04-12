package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Item;

import java.util.List;

public class BookDTO extends Book {

    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
