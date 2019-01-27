package com.komRAD.model.listing;

import com.komRAD.model.Game;

import java.util.List;

public class GameListing {

    private final List<Game> games;
    private final Integer numberOfGames;

    public GameListing(List<Game> gameList, int gameListSize) {
        this.games = gameList;
        this.numberOfGames = gameListSize;
    }

    public List<Game> getGames() {
        return games;
    }

    public Integer getNumberOfGames() {
        return numberOfGames;
    }

    @Override
    public String toString() {
        return "GameListing{" +
                "games=" + games +
                ", numberOfGames=" + numberOfGames +
                '}';
    }
}
