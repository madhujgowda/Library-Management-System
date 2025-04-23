package com.github.librarymanagementsystem.service;

import com.github.librarymanagementsystem.dto.GameDTO;
import com.github.librarymanagementsystem.dto.MovieDTO;
import com.github.librarymanagementsystem.entity.*;
import com.github.librarymanagementsystem.mapper.GameMapper;
import com.github.librarymanagementsystem.mapper.MovieMapper;
import com.github.librarymanagementsystem.repo.*;
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

    private ItemStatusRepo itemStatusRepo;

    public GameServiceImpl (GameRepo gameRepo, GameMapper gameMapper, ItemRepo itemRepo, ItemTypeRepo itemTypeRepo, ItemStatusRepo itemStatusRepo) {
        this.gameRepo = gameRepo;
        this.gameMapper = gameMapper;
        this.itemRepo = itemRepo;
        this.itemTypeRepo = itemTypeRepo;
        this.itemStatusRepo = itemStatusRepo;
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

    @Override
    public Game getGameById(Long gameId) {
        Optional<Game> gameResult = gameRepo.findById(gameId);

        Game game = null;
        if (gameResult.isPresent()) {
            game = gameResult.get();
        }

        return game;
    }

    @Override
    public Game addGame(GameDTO gameDTO) {
        Game game = gameMapper.mapGameDTODetails(gameDTO);

        game = gameRepo.save(game);

        Optional<ItemType> gameItemType = itemTypeRepo.findAll().stream().filter(itemType -> itemType.getType().equals("game")).findFirst();
        Optional<ItemStatus> availableItemStatus = itemStatusRepo.findAll().stream().filter(itemStatus -> itemStatus.getStatus().equals("Available")).findFirst();

        for (int i = 1; i <= gameDTO.getNoOfCopies(); i++) {
            Item item = new Item();
            item.setMediaId(game.getId());
            item.setItemType(gameItemType.get());
            item.setItemStatus(availableItemStatus.get());
            itemRepo.save(item);
        }

        return game;
    }

    @Override
    public Game updateGame(Game game) {
        return gameRepo.save(game);
    }
}
