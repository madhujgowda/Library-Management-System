package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.BookDTO;
import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.service.interfaces.BookService;
import com.github.librarymanagementsystem.service.interfaces.GameService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    public GameController (GameService gameService) {
        this.gameService = gameService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<GameDTO> listAllGames() {
        return gameService.listAllGames();
    }
}
