package com.github.librarymanagementsystem.service.interfaces;

import com.github.librarymanagementsystem.dto.GameDTO;

import java.util.List;

public interface GameService {

    List<GameDTO> listAllGames();
}
