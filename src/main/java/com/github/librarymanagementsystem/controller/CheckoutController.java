package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.CheckoutDTO;
import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.entity.Checkout;
import com.github.librarymanagementsystem.entity.Game;
import com.github.librarymanagementsystem.service.interfaces.CheckoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    public CheckoutController (CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<CheckoutDTO> listAllCheckedItems() {
        return checkoutService.listAllCheckedItems();
    }

    @ResponseBody
    @RequestMapping("/user/list/{userId}")
    public List<CheckoutDTO> listAllUserCheckedItems(@PathVariable("userId") Long userId) {
        return checkoutService.listAllUserCheckedItems(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Checkout createCheckout(@RequestBody Checkout checkout) {
        if (checkout == null) {
            throw new IllegalStateException("Please submit a checkout.");
        }

        return checkoutService.createCheckout(checkout);
    }

    @ResponseBody
    @RequestMapping(path = "/return/{checkoutId}", method = RequestMethod.DELETE)
    public String returnCheckout(@PathVariable("checkoutId") Long checkoutId) {
        return checkoutService.returnCheckout(checkoutId);
    }

    @ResponseBody
    @RequestMapping(path = "/return/item/{itemId}", method = RequestMethod.DELETE)
    public String returnItem(@PathVariable("itemId") Long itemId) {
        return checkoutService.returnItem(itemId);
    }
}
