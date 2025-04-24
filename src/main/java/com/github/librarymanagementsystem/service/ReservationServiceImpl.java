package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemStatus;
import com.github.librarymanagementsystem.entity.Reservation;
import com.github.librarymanagementsystem.entity.User;
import com.github.librarymanagementsystem.repo.*;
import com.github.librarymanagementsystem.repo.ReservationRepo;
import com.github.librarymanagementsystem.service.interfaces.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepo reservationRepo;

    private ItemRepo itemRepo;

    private ItemStatusRepo itemStatusRepo;

    private UserRepo userRepo;

    public ReservationServiceImpl(ReservationRepo reservationRepo, ItemRepo itemRepo, ItemStatusRepo itemStatusRepo, UserRepo userRepo) {
        this.reservationRepo = reservationRepo;
        this.itemRepo = itemRepo;
        this.itemStatusRepo = itemStatusRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        Optional<User> userResult = userRepo.findById(reservation.getUser().getId());
        if (userResult.isPresent()) {
            List<Reservation> reservationList = reservationRepo.findByUserId(reservation.getUser().getId());

            if (reservationList.size() < userResult.get().getUserType().getMaxReservation()) {
                Optional<Item> itemResult = itemRepo.findById(reservation.getItem().getId());

                if (itemResult.isPresent()) {
                    Optional<ItemStatus> onHoldItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("On-Hold")).findFirst();

                    Item item = itemResult.get();
                    item.setItemStatus(onHoldItemStatus.get());
                    itemRepo.save(item);

                    reservation.setDate(new Date());
                    return reservationRepo.save(reservation);
                }
            } else {
                throw new IllegalStateException("Max Reservation reached.");
            }
        }

        return reservation;
    }
}
