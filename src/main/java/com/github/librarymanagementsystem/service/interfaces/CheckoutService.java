package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.CheckoutDTO;
import com.github.librarymanagementsystem.entity.Checkout;
import org.hibernate.annotations.Check;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CheckoutService {

    List<CheckoutDTO> listAllCheckedItems();

    List<CheckoutDTO> listAllUserCheckedItems(Long userId);

    Checkout createCheckout(Checkout checkout);

    String returnCheckout(Long checkoutId);

    String returnItem(Long itemId);
}
