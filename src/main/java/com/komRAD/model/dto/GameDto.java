package com.komRAD.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.komRAD.enums.GameTypes;
import com.komRAD.model.Game;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {

    private final Long id;
    private final String title;
    private final String author;
    private final Integer numberOfPlayers;
    private final GameTypes gameType;


    private GameDto(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("author") String author,
            @JsonProperty("numberOfPlayers") Integer numberOfPlayers,
            @JsonProperty("gameType") GameTypes gameType) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPlayers = numberOfPlayers;
        this.gameType = gameType;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public GameTypes getGameType() {
        return gameType;
    }

    public Game toDomain(){return new Game(title,author,numberOfPlayers,gameType);}

    public static GameDto fromDomain(Game game) {
        return new GameDto(game.getGameId(),
                game.getTitle(),
                game.getAuthor(),
                game.getNumberOfPlayers(),
                game.getGameType());}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDto gameDto = (GameDto) o;
        return Objects.equals(id, gameDto.id) &&
                Objects.equals(title, gameDto.title) &&
                Objects.equals(author, gameDto.author) &&
                Objects.equals(numberOfPlayers, gameDto.numberOfPlayers) &&
                gameType == gameDto.gameType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, numberOfPlayers, gameType);
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", numberOfPlayers=" + numberOfPlayers +
                ", gameType=" + gameType +
                '}';
    }
}
