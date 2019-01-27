package com.komRAD.model.listing;

import com.komRAD.model.Player;

import java.util.List;

public class PlayerListing {

    private final List<Player> players;
    private final Integer numberOfPlayers;

    public PlayerListing(List<Player> playersList, int playersListSize) {
        this.players = playersList;
        this.numberOfPlayers = playersListSize;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public String toString() {
        return "PlayerListing{" +
                "players=" + players +
                ", numberOfPlayers=" + numberOfPlayers +
                '}';
    }
}
