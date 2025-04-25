package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.FineDTO;
import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.repo.*;
import com.github.librarymanagementsystem.service.interfaces.FineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FineServiceImpl implements FineService {
    private FineRepo fineRepo;

    private FineStatusRepo fineStatusRepo;

    private BookRepo bookRepo;

    private MovieRepo movieRepo;

    private GameRepo gameRepo;

    public FineServiceImpl(FineRepo fineRepo, BookRepo bookRepo, MovieRepo movieRepo, GameRepo gameRepo, FineStatusRepo fineStatusRepo) {
        this.fineRepo = fineRepo;
        this.bookRepo = bookRepo;
        this.movieRepo = movieRepo;
        this.gameRepo = gameRepo;
        this.fineStatusRepo = fineStatusRepo;
    }
    @Override
    public List<FineDTO> listAllFines() {
        List<Fine> fineList = fineRepo.findAll();

        return mapFineDetails(fineList);
    }

    @Override
    public List<FineDTO> listAllUserFines(Long userId) {
        List<Fine> fineList = fineRepo.findByUserId(userId);
        return mapFineDetails(fineList);
    }

    private List<FineDTO> mapFineDetails(List<Fine> fineList) {
        List<FineDTO> fineDTOList = new ArrayList<>();

        for(Fine fine: fineList){
            FineDTO fineDTO = new FineDTO();
            fineDTO.setId(fine.getId());
            fineDTO.setUser(fine.getUser());
            fineDTO.setItem(fineDTO.getItem());
            fineDTO.setAmount(fine.getAmount());
            fineDTO.setDate(fine.getDate());
            fineDTO.setFineStatus(fine.getFineStatus());

            String itemType = fine.getItem().getItemType().getType();
            if (itemType.equals("book")) {
                Optional<Book> bookResult = bookRepo.findById(fine.getItem().getMediaId());
                if (bookResult.isPresent()) {
                    fineDTO.setTitle(bookResult.get().getTitle());
                }
            } else if (itemType.equals("movie")) {
                Optional<Movie> movieResult = movieRepo.findById(fine.getItem().getMediaId());
                if (movieResult.isPresent()) {
                    fineDTO.setTitle(movieResult.get().getTitle());
                }
            } else {
                Optional<Game> gameResult = gameRepo.findById(fine.getItem().getMediaId());
                if (gameResult.isPresent()) {
                    fineDTO.setTitle(gameResult.get().getTitle());
                }
            }
            fineDTOList.add(fineDTO);
        }

        return fineDTOList;
    }

    @Override
    public Fine payFine(Long fineId) {
        Optional<Fine> fineResult = fineRepo.findById(fineId);

        if (fineResult.isPresent()) {
            Fine fine = fineResult.get();

            Optional<FineStatus> paidStatus = fineStatusRepo.findAll().stream().filter(fineStatus -> fineStatus.getStatus().equals("Paid")).findFirst();
            fine.setFineStatus(paidStatus.get());

            fineRepo.save(fine);
        }
        return fineResult.get();
    }

    @Override
    public String deleteFine(Long fineId) {
        Optional<Fine> fineResult = fineRepo.findById(fineId);

        if (fineResult.isPresent()) {
            fineRepo.delete(fineResult.get());
            return "Success";
        } else {
            throw new IllegalStateException("Failed to delete. Please try again");
        }
    }
}
