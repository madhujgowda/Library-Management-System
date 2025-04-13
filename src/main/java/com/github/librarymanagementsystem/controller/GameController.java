package com.github.librarymanagementsystem.controller;

import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.entity.Game;
import com.github.librarymanagementsystem.service.interfaces.GameService;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Game addGame(@RequestBody GameDTO gameDTO) {
        if (gameDTO == null) {
            throw new IllegalStateException("Please submit a game to add.");
        }

        return gameService.addGame(gameDTO);
    }
}
