package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.SavedItemRequest;
import com.github.librarymanagementsystem.entity.SavedItem;
import org.springframework.web.bind.annotation.RequestBody;

public interface SavedItemService {

    SavedItem createSavedItem(SavedItemRequest savedItemRequest);
}
