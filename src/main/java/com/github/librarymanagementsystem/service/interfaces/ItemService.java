package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.ItemRequest;
import com.github.librarymanagementsystem.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> findByItemTypeAndMediaId(ItemRequest itemRequest);
}
