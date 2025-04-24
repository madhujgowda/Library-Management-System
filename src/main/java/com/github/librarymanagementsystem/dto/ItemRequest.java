package com.github.librarymanagementsystem.dto;

import jakarta.persistence.Column;

public class ItemRequest {

    Long mediaId;

    String itemType;

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}
