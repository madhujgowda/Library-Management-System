package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.Game;
import com.github.librarymanagementsystem.entity.Item;
import com.github.librarymanagementsystem.entity.ItemType;
import com.github.librarymanagementsystem.entity.Movie;
import com.github.librarymanagementsystem.mapper.GameMapper;
import com.github.librarymanagementsystem.mapper.MovieMapper;
import com.github.librarymanagementsystem.repo.GameRepo;
import com.github.librarymanagementsystem.repo.ItemRepo;
import com.github.librarymanagementsystem.repo.ItemTypeRepo;
import com.github.librarymanagementsystem.repo.MovieRepo;
import com.github.librarymanagementsystem.service.interfaces.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private GameRepo gameRepo;

    private GameMapper gameMapper;

    private ItemRepo itemRepo;

    private ItemTypeRepo itemTypeRepo;

    public GameServiceImpl (GameRepo gameRepo, GameMapper gameMapper, ItemRepo itemRepo, ItemTypeRepo itemTypeRepo) {
        this.gameRepo = gameRepo;
        this.gameMapper = gameMapper;
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
    }
    @Override
    public List<GameDTO> listAllGames() {
        List<Game> gameList = gameRepo.findAll();
        List<GameDTO> gameDTOList = new ArrayList<>();

        for (Game game: gameList) {
            GameDTO gameDTO = gameMapper.mapGameDetails(game);

            Optional<ItemType> gameItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("game")).findFirst();

            List<Item> itemList = itemRepo.findByItemTypeIdAndMediaId(gameItemType.get().getId(), game.getId());

            gameDTO.setItems(itemList);

            gameDTOList.add(gameDTO);
        }

        return gameDTOList;
    }
}
