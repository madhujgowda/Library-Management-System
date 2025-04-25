package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.CheckoutDTO;
import com.github.librarymanagementsystem.dto.FineDTO;
import com.github.librarymanagementsystem.entity.Fine;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface FineService {
    List<FineDTO> listAllFines();

    Fine payFine(Long fineId);

    String deleteFine(Long fineId);
}
