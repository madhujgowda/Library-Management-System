package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.ReservationDTO;
import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.mapper.ReservationMapper;
import com.github.librarymanagementsystem.repo.*;
import com.github.librarymanagementsystem.repo.ReservationRepo;
import com.github.librarymanagementsystem.service.interfaces.ReservationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationRepo reservationRepo;

    private ReservationMapper reservationMapper;

    private ItemRepo itemRepo;

    private ItemStatusRepo itemStatusRepo;

    private UserRepo userRepo;

    private BookRepo bookRepo;

    private MovieRepo movieRepo;

    private GameRepo gameRepo;

    public ReservationServiceImpl(ReservationRepo reservationRepo, ReservationMapper reservationMapper, ItemRepo itemRepo, ItemStatusRepo itemStatusRepo, UserRepo userRepo, BookRepo bookRepo, MovieRepo movieRepo, GameRepo gameRepo) {
        this.reservationRepo = reservationRepo;
        this.reservationMapper = reservationMapper;
        this.itemRepo = itemRepo;
        this.itemStatusRepo = itemStatusRepo;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.movieRepo = movieRepo;
        this.gameRepo = gameRepo;
    }

    @Override
    public List<ReservationDTO> listAllReservations() {
        List<Reservation> reservationList = reservationRepo.findAll();

        return mapReservationDetails(reservationList);
    }

    @Override
    public List<ReservationDTO> getReservationById(Long reservationId) {
        Optional<Reservation> reservationResult = reservationRepo.findById(reservationId);

        List<ReservationDTO> reservationDTOList = new ArrayList<>();

        if (reservationResult.isPresent()) {
            List<Reservation> reservationList = new ArrayList<>();
            reservationList.add(reservationResult.get());

            reservationDTOList = mapReservationDetails(reservationList);
        }


        return reservationDTOList;
    }

    @Override
    public List<ReservationDTO> getReservationByUserId(Long userId) {
        List<Reservation> reservationList = reservationRepo.findByUserId(userId);

        return mapReservationDetails(reservationList);
    }

    @Override
    public List<ReservationDTO> getReservationByItemId(Long itemId) {
        List<Reservation> reservationList = reservationRepo.findByItemId(itemId);

        return mapReservationDetails(reservationList);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        Optional<User> userResult = userRepo.findById(reservation.getUser().getId());
        if (userResult.isPresent()) {
            List<Reservation> reservationList = reservationRepo.findByUserId(reservation.getUser().getId());

            if (reservationList.size() < userResult.get().getUserType().getMaxReservation()) {
                Optional<Item> itemResult = itemRepo.findById(reservation.getItem().getId());

                if (itemResult.isPresent()) {
                    Item item = itemResult.get();

                    if (!item.getItemStatus().getStatus().equals("Checked-Out")) {
                        Optional<ItemStatus> onHoldItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("On-Hold")).findFirst();

                        item.setItemStatus(onHoldItemStatus.get());
                        itemRepo.save(item);
                    }

                    reservation.setDate(new Date());
                    return reservationRepo.save(reservation);
                }
            } else {
                throw new IllegalStateException("Max Reservation reached.");
            }
        }

        return reservation;
    }

    @Override
    public String deleteReservation(Long reservationId) {
        Optional<Reservation> reservationResult = reservationRepo.findById(reservationId);

        if (reservationResult.isPresent()) {
            Optional<Item> itemResult = itemRepo.findById(reservationResult.get().getItem().getId());

            if (itemResult.isPresent()) {
                Item item = itemResult.get();

                if (!item.getItemStatus().getStatus().equals("Checked-Out")) {
                    Optional<ItemStatus> availableItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Available")).findFirst();
                    item.setItemStatus(availableItemStatus.get());

                    itemRepo.save(item);
                }
            }

            reservationRepo.delete(reservationResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to delete. Please try again");
        }
    }

    private List<ReservationDTO> mapReservationDetails(List<Reservation> reservationList){
        List<ReservationDTO> reservationDTOList = new ArrayList<>();

        for (Reservation reservation: reservationList) {
            ReservationDTO reservationDTO = reservationMapper.mapReservationDetails(reservation);

            String itemType = reservation.getItem().getItemType().getType();
            if (itemType.equals("book")) {
                Optional<Book> bookResult = bookRepo.findById(reservation.getItem().getMediaId());
                if (bookResult.isPresent()) {
                    reservationDTO.setTitle(bookResult.get().getTitle());
                }
            } else if (itemType.equals("movie")) {
                Optional<Movie> movieResult = movieRepo.findById(reservation.getItem().getMediaId());
                if (movieResult.isPresent()) {
                    reservationDTO.setTitle(movieResult.get().getTitle());
                }
            } else {
                Optional<Game> gameResult = gameRepo.findById(reservation.getItem().getMediaId());
                if (gameResult.isPresent()) {
                    reservationDTO.setTitle(gameResult.get().getTitle());
                }
            }

            reservationDTOList.add(reservationDTO);
        }
        return reservationDTOList;
    }
}
