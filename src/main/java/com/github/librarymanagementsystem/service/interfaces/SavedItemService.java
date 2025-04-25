package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.SavedItemDTO;
import com.github.librarymanagementsystem.dto.SavedItemRequest;
import com.github.librarymanagementsystem.entity.SavedItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SavedItemService {

    List<SavedItemDTO> getSavedItemsByUserId(Long userId);

    List<SavedItemDTO> getSavedItemsByMediaId(Long mediaId);

    SavedItem createSavedItem(SavedItemRequest savedItemRequest);

    String deleteSavedItem(Long savedItemId);
}
