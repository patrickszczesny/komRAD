package com.komRAD.controller;

import com.komRAD.model.Player;
import com.komRAD.model.dto.PlayerDto;
import com.komRAD.model.listing.PlayerListing;
import com.komRAD.service.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    public PlayerRepository playerRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerListing getAllPlayers() {
        List<Player> playersList = StreamSupport.stream(playerRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return new PlayerListing(playersList, playersList.size());
    }

    @GetMapping(value = "/playerById/{playerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerDto getGameById(@PathVariable Long playerId) {
        return PlayerDto.fromDomain(playerRepository.findById(playerId).get());
    }

    @GetMapping(value = "/playerByTitle/{userName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PlayerListing getGamesByTitle(
            @PathVariable String userName) {
        List<Player> playersList = StreamSupport.stream(playerRepository.findAll().spliterator(), false).filter(player -> player.getUserName().equals(userName)).collect(Collectors.toList());
        return new PlayerListing(playersList, playersList.size());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlayer(@RequestBody PlayerDto playerDto) {
        playerRepository.save(playerDto.toDomain());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPlayers() {
        playerRepository.deleteAll();
    }

    @DeleteMapping("/deletById/{playerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePLayerById(@PathVariable Long playerId) {
        playerRepository.deleteById(playerId);

    }

    @PutMapping(value = "update/{playerId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public void updatePlayerById(@PathVariable Long playerId, @RequestBody PlayerDto playerDto) {
        Player playerToUpdate = playerRepository.findById(playerId).get();
        playerToUpdate.setUserName(playerDto.toDomain().getUserName());
        playerToUpdate.setPassword(playerDto.toDomain().getPassword());
        playerToUpdate.setGames(playerDto.toDomain().getGames());
        playerToUpdate.setMeetings(playerDto.toDomain().getMeetings());
        playerRepository.save(playerToUpdate);
    }

}
