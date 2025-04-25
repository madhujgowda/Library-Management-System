package com.github.librarymanagementsystem.dto;

import com.github.librarymanagementsystem.entity.SavedItem;

public class SavedItemRequest {

    Long mediaId;

    Long userId;

    String itemType;

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
