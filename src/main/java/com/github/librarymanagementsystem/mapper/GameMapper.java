package com.github.librarymanagementsystem.mapper;

import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Game;
import com.github.librarymanagementsystem.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameDTO mapGameDetails(Game game) {
        GameDTO gameDTO = new GameDTO();

        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setDescription(game.getDescription());
        gameDTO.setCost(game.getCost());
        gameDTO.setGenre(game.getGenre());
        gameDTO.setPublisher(game.getPublisher());
        gameDTO.setPlatform(game.getPlatform());

        return gameDTO;
    }

    public Game mapGameDTODetails(GameDTO gameDTO) {
        Game game = new Game();

        game.setTitle(gameDTO.getTitle());
        game.setDescription(gameDTO.getDescription());
        game.setCost(gameDTO.getCost());
        game.setGenre(gameDTO.getGenre());
        game.setPublisher(gameDTO.getPublisher());
        game.setPlatform(gameDTO.getPlatform());

        return game;
    }
}
