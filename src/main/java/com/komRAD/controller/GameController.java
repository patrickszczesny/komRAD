package com.komRAD.controller;

import com.komRAD.enums.GameTypes;
import com.komRAD.model.Game;
import com.komRAD.model.dto.GameDto;
import com.komRAD.model.listing.GameListing;
import com.komRAD.service.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    public GameRepository gameRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameListing getAllGames() {
    List<Game> gameList = StreamSupport.stream(gameRepository.findAll().spliterator(),false).collect(Collectors.toList());
    return new GameListing(gameList, gameList.size());
    }

    @GetMapping(value = "/gameById/{gameId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameDto getGameById(@PathVariable Long gameId) {
        return GameDto.fromDomain(gameRepository.findById(gameId).get());
    }

    @GetMapping(value = "/gamesByTitle/{gameTitle}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameListing getGamesByTitle(
            @PathVariable String gameTitle) {
        List<Game> gameList = StreamSupport.stream(gameRepository.findAll().spliterator(), false).filter(game -> game.getTitle().equals(gameTitle)).collect(Collectors.toList());
        return new GameListing(gameList, gameList.size());
    }

    @GetMapping(value = "/gamesByNumberOfPlayers/{gamesNumberOfPlayers}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameListing getGamesByNumberOfPlayers(
            @PathVariable Integer gamesNumberOfPlayers) {
        List<Game> gameList = StreamSupport.stream(gameRepository.findAll().spliterator(), false).filter(game -> game.getNumberOfPlayers().equals(gamesNumberOfPlayers)).collect(Collectors.toList());
        return new GameListing(gameList, gameList.size());
    }

    @GetMapping(value = "/gamesByAuthor/{gamesAuthor}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameListing getGamesByAuthor(
            @PathVariable String gamesAuthor
    ) {
        List<Game> gameList = StreamSupport.stream(gameRepository.findAll().spliterator(), false).filter(game -> game.getAuthor().equals(gamesAuthor)).collect(Collectors.toList());
        return new GameListing(gameList, gameList.size());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addGame(@RequestBody GameDto gameDto) {
        gameRepository.save(gameDto.toDomain());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllGames() {
        gameRepository.deleteAll();
    }

    @DeleteMapping("/deletById/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long gameId) {
        gameRepository.deleteById(gameId);

    }

    @PostMapping(value = "update/{gameId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public void updateGameById(@PathVariable Long gameId, @RequestBody GameDto gameDto) {
        Game gameToUpdate = gameRepository.findById(gameId).get();
        gameToUpdate.setAuthor(gameDto.toDomain().getAuthor());
        gameToUpdate.setGameType(gameDto.toDomain().getGameType());
        gameToUpdate.setTitle(gameDto.toDomain().getTitle());
        gameToUpdate.setNumberOfPlayers(gameDto.toDomain().getNumberOfPlayers());
        gameRepository.save(gameToUpdate);
    }

}
