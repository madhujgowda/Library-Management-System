package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item, Long> {

    List<Item> findByItemTypeIdAndMediaId(Long itemTypeId, Long mediaId);
}
