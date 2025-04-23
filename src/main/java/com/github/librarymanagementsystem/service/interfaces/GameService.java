package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.entity.Game;

import java.util.List;

public interface GameService {

    List<GameDTO> listAllGames();

    Game addGame(GameDTO gameDTO);

    Game getGameById(Long gameId);

    Game updateGame(Game game);

}
