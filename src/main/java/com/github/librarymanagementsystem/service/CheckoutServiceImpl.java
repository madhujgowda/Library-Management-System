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

    private ReservationRepo reservationRepo;

    private FineRepo fineRepo;

    private FineStatusRepo fineStatusRepo;

    public CheckoutServiceImpl(CheckoutRepo checkoutRepo, CheckoutMapper checkoutMapper,
                               UserRepo userRepo, ItemRepo itemRepo, ItemStatusRepo itemStatusRepo,
                               BookRepo bookRepo, MovieRepo movieRepo, GameRepo gameRepo,
                               ReservationRepo reservationRepo, FineRepo fineRepo, FineStatusRepo fineStatusRepo) {
        this.checkoutRepo = checkoutRepo;
        this.checkoutMapper = checkoutMapper;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
        this.itemStatusRepo = itemStatusRepo;
        this.bookRepo = bookRepo;
        this.movieRepo = movieRepo;
        this.gameRepo = gameRepo;
        this.reservationRepo = reservationRepo;
        this.fineRepo = fineRepo;
        this.fineStatusRepo = fineStatusRepo;
    }

    @Override
    public List<CheckoutDTO> listAllCheckedItems() {
        List<Checkout> checkoutList = checkoutRepo.findAll();
        return mapCheckoutDetails(checkoutList);
    }

    @Override
    public List<CheckoutDTO> listAllUserCheckedItems(Long userId) {
        List<Checkout> checkoutList = checkoutRepo.findByUserId(userId);
        return mapCheckoutDetails(checkoutList);
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

    @Override
    public String returnCheckout(Long checkoutId) {
        Optional<Checkout> checkoutResult = checkoutRepo.findById(checkoutId);
        if (checkoutResult.isPresent()) {
            Item item = checkoutResult.get().getItem();

            List<Reservation> reservationList = reservationRepo.findByItemId(checkoutResult.get().getItem().getId());

            if (reservationList.size() > 0) {
                Optional<ItemStatus> onHoldItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("On-Hold")).findFirst();
                item.setItemStatus(onHoldItemStatus.get());
            } else {
                Optional<ItemStatus> availableItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Available")).findFirst();
                item.setItemStatus(availableItemStatus.get());
            }
            itemRepo.save(item);

            if (isDueDateBeforeToday(checkoutResult.get().getDueDate())) {
                Fine fine = new Fine();
                fine.setUser(checkoutResult.get().getUser());
                fine.setItem(item);
                fine.setDate(new Date());
                fine.setAmount(calculateFine(checkoutResult.get()));
                Optional<FineStatus> unPaidStatus = fineStatusRepo.findAll().stream().filter(fineStatus -> fineStatus.getStatus().equals("Unpaid")).findFirst();
                fine.setFineStatus(unPaidStatus.get());
                fineRepo.save(fine);
            }

            checkoutRepo.delete(checkoutResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to return. Please try again");
        }
    }

    @Override
    public String returnItem(Long itemId) {
        List<Checkout> checkoutList = checkoutRepo.findByItemId(itemId);
        if (!checkoutList.isEmpty()) {
            Item item = checkoutList.get(0).getItem();

            List<Reservation> reservationList = reservationRepo.findByItemId(checkoutList.get(0).getItem().getId());

            if (reservationList.size() > 0) {
                Optional<ItemStatus> onHoldItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("On-Hold")).findFirst();
                item.setItemStatus(onHoldItemStatus.get());
            } else {
                Optional<ItemStatus> availableItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Available")).findFirst();
                item.setItemStatus(availableItemStatus.get());
            }
            itemRepo.save(item);

            if (isDueDateBeforeToday(checkoutList.get(0).getDueDate())) {
                Fine fine = new Fine();
                fine.setUser(checkoutList.get(0).getUser());
                fine.setItem(item);
                fine.setDate(new Date());
                fine.setAmount(calculateFine(checkoutList.get(0)));
                Optional<FineStatus> unPaidStatus = fineStatusRepo.findAll().stream().filter(fineStatus -> fineStatus.getStatus().equals("Unpaid")).findFirst();
                fine.setFineStatus(unPaidStatus.get());
                fineRepo.save(fine);
            }

            checkoutRepo.delete(checkoutList.get(0));
            return "Success";
        } else {
            throw new IllegalStateException("Failed to return. Please try again");
        }
    }

    private List<CheckoutDTO> mapCheckoutDetails(List<Checkout> checkoutList) {
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

    private Float calculateFine(Checkout checkout) {
        Float lateFee = checkout.getItem().getItemType().getLateFee();

        int dueDays = getDaysBetween(new Date(), checkout.getDueDate());
        Float amount = lateFee * dueDays;

        //check if the amount is more than item price
        Float cost = 0F;
        String itemType = checkout.getItem().getItemType().getType();
        if (itemType.equals("book")) {
            Optional<Book> bookResult = bookRepo.findById(checkout.getItem().getMediaId());
            if (bookResult.isPresent()) {
                cost = bookResult.get().getCost();
            }
        } else if (itemType.equals("movie")) {
            Optional<Movie> movieResult = movieRepo.findById(checkout.getItem().getMediaId());
            if (movieResult.isPresent()) {
                cost = movieResult.get().getCost();
            }
        } else {
            Optional<Game> gameResult = gameRepo.findById(checkout.getItem().getMediaId());
            if (gameResult.isPresent()) {
                cost = gameResult.get().getCost();
            }
        }
        if (amount > cost) {
            amount = cost;
        }
        return amount;
    }

    public int getDaysBetween(Date d1, Date d2) {
        long diff = d1.getTime() - d2.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return (int) diffDays;
    }

    public boolean isDueDateBeforeToday(Date dueDate) {
        Date today = new Date();

        // Create Calendar instances for both dates
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(today);
        cal2.setTime(dueDate);

        // Compare the year, month, and day
        if (cal2.get(Calendar.YEAR) < cal1.get(Calendar.YEAR)) {
            return true;
        } else if (cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)
                && cal2.get(Calendar.MONTH) < cal1.get(Calendar.MONTH)) {
            return true;
        } else if (cal2.get(Calendar.YEAR) == cal1.get(Calendar.YEAR)
                && cal2.get(Calendar.MONTH) == cal1.get(Calendar.MONTH)
                && cal2.get(Calendar.DAY_OF_MONTH) < cal1.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }
}
