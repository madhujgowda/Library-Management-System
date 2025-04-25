package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.ItemRequest;
import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.dto.SavedItemDTO;
import com.github.librarymanagementsystem.dto.SavedItemRequest;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.SavedItem;
import com.github.librarymanagementsystem.service.interfaces.SavedItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saved-item")
public class SavedItemController {

    private SavedItemService savedItemService;

    public SavedItemController(SavedItemService savedItemService) {
        this.savedItemService = savedItemService;
    }

    @ResponseBody
    @RequestMapping("/user/{userId}")
    public List<SavedItemDTO> getRSavedItemsByUserId(@PathVariable("userId") Long userId) {
        return savedItemService.getRSavedItemsByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public SavedItem createSavedItem(@RequestBody SavedItemRequest savedItemRequest) {
        return savedItemService.createSavedItem(savedItemRequest);
    }
}
