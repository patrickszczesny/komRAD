package com.komRAD.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PLAYERS")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PLAYER_ID")
    private Long playerId;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PLAYERS_GAMES",
            joinColumns = @JoinColumn(name = "PLAYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "GAME_ID"))
    private Set<Game> games = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "PLAYERS_MEETINGS",
            joinColumns = @JoinColumn(name = "PLAYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEETING_ID"))
    private Set<Meeting> meetings = new HashSet<>();

    public Player() {
    }

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Player(String userName, String password, Set<Game> games, Set<Meeting> meetings) {
        this.userName = userName;
        this.password = password;
        this.games = games;
        this.meetings = meetings;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId) &&
                Objects.equals(userName, player.userName) &&
                Objects.equals(password, player.password) &&
                Objects.equals(games, player.games) &&
                Objects.equals(meetings, player.meetings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, userName, password, games, meetings);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", games=" + games +
                ", meetings=" + meetings +
                '}';
    }
}
