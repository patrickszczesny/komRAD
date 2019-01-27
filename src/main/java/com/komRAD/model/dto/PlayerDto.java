package com.komRAD.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.komRAD.enums.GameTypes;
import com.komRAD.model.Game;
import com.komRAD.model.Player;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {

    private final Long id;
    private final String userName;
    private final String password;

    public PlayerDto(
            @JsonProperty("id") Long id,
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Player toDomain() {
        return new Player(userName, password);
    }

    public static PlayerDto fromDomain(Player player) {
        return new PlayerDto(player.getId(),
                player.getUserName(),
                player.getPassword());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return Objects.equals(id, playerDto.id) &&
                Objects.equals(userName, playerDto.userName) &&
                Objects.equals(password, playerDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password);
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
