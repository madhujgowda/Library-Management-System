package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.CheckoutDTO;
import com.github.librarymanagementsystem.dto.FineDTO;
import com.github.librarymanagementsystem.entity.Checkout;
import com.github.librarymanagementsystem.entity.Fine;
import com.github.librarymanagementsystem.service.interfaces.FineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fine")
public class FineController {

    private FineService fineService;

    public FineController (FineService fineService) {
        this.fineService = fineService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<FineDTO> listAllFines() {
        return fineService.listAllFines();
    }

    @ResponseBody
    @RequestMapping("/user/list/{userId}")
    public List<FineDTO> listAllUserFines(@PathVariable("userId") Long userId) {
        return fineService.listAllUserFines(userId);
    }

    @ResponseBody
    @RequestMapping(path = "/pay/{fineId}", method = RequestMethod.POST)
    public Fine payFine(@PathVariable("fineId") Long fineId) {
        if (fineId == null) {
            throw new IllegalStateException("Please submit a fine.");
        }

        return fineService.payFine(fineId);
    }

    @ResponseBody
    @RequestMapping(path = "/delete/{fineId}", method = RequestMethod.DELETE)
    public String deleteFine(@PathVariable("fineId") Long fineId) {
        return fineService.deleteFine(fineId);
    }
}
