package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.SavedItemRequest;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.entity.SavedItem;
import com.github.librarymanagementsystem.entity.User;
import com.github.librarymanagementsystem.repo.ItemTypeRepo;
import com.github.librarymanagementsystem.repo.SavedItemRepo;
import com.github.librarymanagementsystem.repo.UserRepo;
import com.github.librarymanagementsystem.service.interfaces.SavedItemService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SavedItemServiceImpl implements SavedItemService {

    private SavedItemRepo savedItemRepo;

    private UserRepo userRepo;

    private ItemTypeRepo itemTypeRepo;

    public SavedItemServiceImpl (SavedItemRepo savedItemRepo, UserRepo userRepo, ItemTypeRepo itemTypeRepo) {
        this.savedItemRepo = savedItemRepo;
        this.userRepo = userRepo;
        this.itemTypeRepo = itemTypeRepo;
    }

    @Override
    public SavedItem createSavedItem(SavedItemRequest savedItemRequest) {
        SavedItem savedItem = new SavedItem();

        savedItem.setMediaId(savedItemRequest.getMediaId());

        if (savedItemRequest.getItemType().equals("Book")) {
            Optional<ItemType> bookItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("book")).findFirst();
            savedItem.setItemType(bookItemType.get());
        } else if (savedItemRequest.getItemType().equals("Movie")) {
            Optional<ItemType> movieItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("movie")).findFirst();
            savedItem.setItemType(movieItemType.get());
        } else {
            Optional<ItemType> gameItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("game")).findFirst();
            savedItem.setItemType(gameItemType.get());
        }

        Optional<User> userResult = userRepo.findById(savedItemRequest.getUserId());
        savedItem.setUser(userResult.get());
        savedItem.setDate(new Date());

        return savedItemRepo.save(savedItem);
    }
}
