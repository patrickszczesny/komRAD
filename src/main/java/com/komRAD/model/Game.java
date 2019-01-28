package com.komRAD.model;

import com.komRAD.enums.GameTypes;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameId;

    private String title;
    private String author;
    private Integer numberOfPlayers;
    private GameTypes gameType;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "games")
    private Set<Player> players;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_meetings",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id"))
    private Set<Meeting> meetings;

    public Game() {
    }

    public Game(String title, String author, Integer numberOfPlayers, GameTypes gameType) {
        this.title = title;
        this.author = author;
        this.numberOfPlayers = numberOfPlayers;
        this.gameType = gameType;
    }

    public Long getGameId() {
        return gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public GameTypes getGameType() {
        return gameType;
    }

    public void setGameType(GameTypes gameType) {
        this.gameType = gameType;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameId, game.gameId) &&
                Objects.equals(title, game.title) &&
                Objects.equals(numberOfPlayers, game.numberOfPlayers) &&
                gameType == game.gameType &&
                Objects.equals(players, game.players) &&
                Objects.equals(meetings, game.meetings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, title, numberOfPlayers, gameType, players, meetings);
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                ", gameType=" + gameType +
                ", players=" + players +
                ", meetings=" + meetings +
                '}';
    }
}
