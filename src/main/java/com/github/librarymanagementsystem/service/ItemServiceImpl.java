package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.ItemRequest;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.repo.ItemRepo;
import com.github.librarymanagementsystem.repo.ItemTypeRepo;
import com.github.librarymanagementsystem.service.interfaces.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepo itemRepo;

    private ItemTypeRepo itemTypeRepo;

    public ItemServiceImpl(ItemRepo itemRepo, ItemTypeRepo itemTypeRepo) {
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
    }
    @Override
    public List<Item> findByItemTypeAndMediaId(ItemRequest itemRequest) {
        Long itemTypeId;
        if (itemRequest.getItemType().equals("Book")) {
            Optional<ItemType> bookItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("book")).findFirst();
            itemTypeId = bookItemType.get().getId();
        } else if (itemRequest.getItemType().equals("Movie")) {
            Optional<ItemType> movieItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("movie")).findFirst();
            itemTypeId = movieItemType.get().getId();
        } else {
            Optional<ItemType> gameItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("game")).findFirst();
            itemTypeId = gameItemType.get().getId();
        }

        return itemRepo.findByItemTypeIdAndMediaId(itemTypeId, itemRequest.getMediaId());
    }
}
