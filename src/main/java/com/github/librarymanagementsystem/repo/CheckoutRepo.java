package com.github.librarymanagementsystem.repo;

import com.github.librarymanagementsystem.entity.Book;
import com.github.librarymanagementsystem.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepo extends JpaRepository<Checkout, Long> {
    List<Checkout> findByUserId(Long userId);

    List<Checkout> findByItemId(Long itemId);
}
