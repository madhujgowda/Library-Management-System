package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.*;
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
    public List<SavedItemDTO> getSavedItemsByUserId(@PathVariable("userId") Long userId) {
        return savedItemService.getSavedItemsByUserId(userId);
    }

    @ResponseBody
    @RequestMapping("/media/{mediaId}")
    public List<SavedItemDTO> getSavedItemsByMediaId(@PathVariable("mediaId") Long mediaId) {
        return savedItemService.getSavedItemsByMediaId(mediaId);
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public SavedItem createSavedItem(@RequestBody SavedItemRequest savedItemRequest) {
        return savedItemService.createSavedItem(savedItemRequest);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{savedItemId}", method = RequestMethod.DELETE)
    public String deleteSavedItem(@PathVariable("savedItemId") Long savedItemId) {
        return savedItemService.deleteSavedItem(savedItemId);
    }
}
