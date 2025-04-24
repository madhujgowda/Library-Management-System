package com.github.librarymanagementsystem.controller;


import com.github.librarymanagementsystem.dto.ItemRequest;
import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.service.interfaces.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    public ItemController (ItemService itemService) {
        this.itemService = itemService;
    }

    @ResponseBody
    @RequestMapping(path = "/list", method = RequestMethod.POST)
    public List<Item> findByItemTypeAndMediaId(@RequestBody ItemRequest itemRequest) {
        return itemService.findByItemTypeAndMediaId(itemRequest);
    }
}
