package com.komRAD.controller;

import com.komRAD.enums.GameTypes;
import com.komRAD.model.Game;
import com.komRAD.model.listing.GameListing;
import com.komRAD.service.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    public GameRepository gameRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GameListing getListing(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "numberOfPlayers", required = false) Integer numberOfPlayers,
            @RequestParam(value = "gameType", required = false) GameTypes gameType){
    List<Game> gameList = StreamSupport.stream(gameRepository.findAll().spliterator(),false).collect(Collectors.toList());
    return new GameListing(gameList, gameList.size());
    }



}