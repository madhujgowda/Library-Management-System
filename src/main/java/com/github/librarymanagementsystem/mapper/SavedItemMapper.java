package com.github.librarymanagementsystem.mapper;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.dto.SavedItemDTO;
import com.github.librarymanagementsystem.entity.Reservation;
import com.github.librarymanagementsystem.entity.SavedItem;
import org.springframework.stereotype.Component;

@Component
public class SavedItemMapper {

    public SavedItemDTO mapSavedItemDetails(SavedItem savedItem) {
        SavedItemDTO savedItemDTO = new SavedItemDTO();

        savedItemDTO.setId(savedItem.getId());
        savedItemDTO.setMediaId(savedItem.getMediaId());
        savedItemDTO.setItemType(savedItem.getItemType());
        savedItemDTO.setUser(savedItem.getUser());
        savedItemDTO.setDate(savedItem.getDate());

        return savedItemDTO;
    }
}
