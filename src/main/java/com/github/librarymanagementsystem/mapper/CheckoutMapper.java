package com.github.librarymanagementsystem.mapper;

import com.github.librarymanagementsystem.dto.CheckoutDTO;
import com.github.librarymanagementsystem.entity.Checkout;
import org.springframework.stereotype.Component;

@Component
public class CheckoutMapper {

    public CheckoutDTO mapCheckoutDetails(Checkout checkout) {
        CheckoutDTO checkoutDTO = new CheckoutDTO();

        checkoutDTO.setId(checkout.getId());
        checkoutDTO.setCheckoutDate(checkout.getCheckoutDate());
        checkoutDTO.setDueDate(checkout.getDueDate());
        checkoutDTO.setItem(checkout.getItem());
        checkoutDTO.setUser(checkout.getUser());
        checkoutDTO.setRenewalCount(checkout.getRenewalCount());

        return checkoutDTO;
    }
}
