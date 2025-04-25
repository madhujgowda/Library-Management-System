package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.repo.*;
import com.github.librarymanagementsystem.service.interfaces.CheckoutService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CheckoutRepo checkoutRepo;

    private UserRepo userRepo;

    private ItemRepo itemRepo;

    private ItemStatusRepo itemStatusRepo;

    private ItemTypeRepo itemTypeRepo;

    public CheckoutServiceImpl(CheckoutRepo checkoutRepo, UserRepo userRepo, ItemRepo itemRepo, ItemStatusRepo itemStatusRepo) {
        this.checkoutRepo = checkoutRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.itemStatusRepo = itemStatusRepo;
    }
    @Override
    public Checkout createCheckout(Checkout checkout) {
        Optional<User> userResult = userRepo.findById(checkout.getUser().getId());

        if (userResult.isPresent()) {
            int maxCheckout = userResult.get().getUserType().getMaxCheckout();

            List<Checkout> checkoutList = checkoutRepo.findByUserId(checkout.getUser().getId());

            if (checkoutList.size() < maxCheckout) {
                Optional<Item> itemResult = itemRepo.findById(checkout.getItem().getId());

                if (itemResult.isPresent()) {
                    Item item = itemResult.get();

                    Optional<ItemStatus> checkedOutItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Checked-Out")).findFirst();
                    item.setItemStatus(checkedOutItemStatus.get());
                    itemRepo.save(item);

                    checkout.setRenewalCount(0L);
                    checkout.setCheckoutDate(new Date());

                    int maxDueDays = item.getItemType().getMaxDueDays();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DAY_OF_MONTH, maxDueDays);
                    Date dueDate = calendar.getTime();

                    checkout.setDueDate(dueDate);
                    return checkoutRepo.save(checkout);
                }
            } else {
                throw new IllegalStateException("Max Checkout reached.");
            }
        }

        throw new IllegalStateException("User not found.");
    }
}
