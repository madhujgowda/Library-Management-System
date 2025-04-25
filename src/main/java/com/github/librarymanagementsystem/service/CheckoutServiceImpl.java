package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.CheckoutDTO;
import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.mapper.CheckoutMapper;
import com.github.librarymanagementsystem.repo.*;
import com.github.librarymanagementsystem.service.interfaces.CheckoutService;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CheckoutRepo checkoutRepo;

    private CheckoutMapper checkoutMapper;

    private UserRepo userRepo;

    private ItemRepo itemRepo;

    private ItemStatusRepo itemStatusRepo;

    private ItemTypeRepo itemTypeRepo;

    private BookRepo bookRepo;

    private MovieRepo movieRepo;

    private GameRepo gameRepo;

    public CheckoutServiceImpl(CheckoutRepo checkoutRepo, CheckoutMapper checkoutMapper,
                               UserRepo userRepo, ItemRepo itemRepo, ItemStatusRepo itemStatusRepo,
                               BookRepo bookRepo, MovieRepo movieRepo, GameRepo gameRepo) {
        this.checkoutRepo = checkoutRepo;
        this.checkoutMapper = checkoutMapper;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.itemStatusRepo = itemStatusRepo;
        this.bookRepo = bookRepo;
        this.movieRepo = movieRepo;
        this.gameRepo = gameRepo;
    }

    @Override
    public List<CheckoutDTO> listAllCheckedItems() {
        List<Checkout> checkoutList = checkoutRepo.findAll();
        List<CheckoutDTO> checkoutDTOList = new ArrayList<>();

        for (Checkout checkout: checkoutList) {
            CheckoutDTO checkoutDTO = checkoutMapper.mapCheckoutDetails(checkout);

            Optional<Item> itemResult = itemRepo.findById(checkout.getItem().getId());

            if (itemResult.isPresent()) {
                String itemType = checkout.getItem().getItemType().getType();
                if (itemType.equals("book")) {
                    Optional<Book> bookResult = bookRepo.findById(checkout.getItem().getMediaId());
                    if (bookResult.isPresent()) {
                        checkoutDTO.setTitle(bookResult.get().getTitle());
                    }
                } else if (itemType.equals("movie")) {
                    Optional<Movie> movieResult = movieRepo.findById(checkout.getItem().getMediaId());
                    if (movieResult.isPresent()) {
                        checkoutDTO.setTitle(movieResult.get().getTitle());
                    }
                } else {
                    Optional<Game> gameResult = gameRepo.findById(checkout.getItem().getMediaId());
                    if (gameResult.isPresent()) {
                        checkoutDTO.setTitle(gameResult.get().getTitle());
                    }
                }
            }
            checkoutDTOList.add(checkoutDTO);
        }

        return checkoutDTOList;
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
