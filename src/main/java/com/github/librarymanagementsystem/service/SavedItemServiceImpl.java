package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.dto.SavedItemDTO;
import com.github.librarymanagementsystem.dto.SavedItemRequest;
import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.mapper.SavedItemMapper;
import com.github.librarymanagementsystem.repo.*;
import com.github.librarymanagementsystem.service.interfaces.SavedItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SavedItemServiceImpl implements SavedItemService {

    private SavedItemRepo savedItemRepo;

    private SavedItemMapper savedItemMapper;

    private UserRepo userRepo;

    private ItemTypeRepo itemTypeRepo;

    private BookRepo bookRepo;

    private MovieRepo movieRepo;

    private GameRepo gameRepo;

    public SavedItemServiceImpl (SavedItemRepo savedItemRepo, UserRepo userRepo, ItemTypeRepo itemTypeRepo, SavedItemMapper savedItemMapper,
                                 BookRepo bookRepo, MovieRepo movieRepo, GameRepo gameRepo) {
        this.savedItemRepo = savedItemRepo;
        this.userRepo = userRepo;
        this.itemTypeRepo = itemTypeRepo;
        this.savedItemMapper = savedItemMapper;
        this.bookRepo = bookRepo;
        this.movieRepo = movieRepo;
        this.gameRepo = gameRepo;
    }

    @Override
    public List<SavedItemDTO> getSavedItemsByUserId(Long userId) {
        List<SavedItem> savedItemList = savedItemRepo.findByUserId(userId);

        return mapSavedItemDetails(savedItemList);
    }

    @Override
    public List<SavedItemDTO> getSavedItemsByMediaId(Long mediaId) {
        List<SavedItem> savedItemList = savedItemRepo.findByMediaId(mediaId);

        return mapSavedItemDetails(savedItemList);
    }

    private List<SavedItemDTO> mapSavedItemDetails(List<SavedItem> savedItemList) {
        List<SavedItemDTO> savedItemDTOList = new ArrayList<>();

        for (SavedItem savedItem: savedItemList) {
            SavedItemDTO savedItemDTO = savedItemMapper.mapSavedItemDetails(savedItem);

            String itemType = savedItem.getItemType().getType();
            if (itemType.equals("book")) {
                Optional<Book> bookResult = bookRepo.findById(savedItem.getMediaId());
                if (bookResult.isPresent()) {
                    savedItemDTO.setTitle(bookResult.get().getTitle());
                }
            } else if (itemType.equals("movie")) {
                Optional<Movie> movieResult = movieRepo.findById(savedItem.getMediaId());
                if (movieResult.isPresent()) {
                    savedItemDTO.setTitle(movieResult.get().getTitle());
                }
            } else {
                Optional<Game> gameResult = gameRepo.findById(savedItem.getMediaId());
                if (gameResult.isPresent()) {
                    savedItemDTO.setTitle(gameResult.get().getTitle());
                }
            }

            savedItemDTOList.add(savedItemDTO);
        }
        return savedItemDTOList;
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

    @Override
    public String deleteSavedItem(Long savedItemId) {
        Optional<SavedItem> savedItemResult = savedItemRepo.findById(savedItemId);

        if (savedItemResult.isPresent()) {
            savedItemRepo.delete(savedItemResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to delete. Please try again");
        }
    }
}
