package com.komRAD.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.komRAD.model.Game;
import com.komRAD.model.Meeting;
import com.komRAD.model.Player;
import com.komRAD.model.Venue;

import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MeetingDto {

    private final Long id;
    private final String dateOfTheMeeting;
    private final Venue venueOfMeeting;
    private final Set<Player> players;
    private final Game game;

    private MeetingDto(
            @JsonProperty("id") Long id,
            @JsonProperty("dateOfTheMeeting") String dateOfTheMeeting,
            @JsonProperty("venueOfMeeting") Venue venueOfMeeting,
            @JsonProperty("players") Set<Player> players,
            @JsonProperty("game") Game game
    ) {
        this.id = id;
        this.dateOfTheMeeting = dateOfTheMeeting;
        this.venueOfMeeting = venueOfMeeting;
        this.players = players;
        this.game = game;
    }


    public Long getId() {
        return id;
    }

    public String getDateOfTheMeeting() {
        return dateOfTheMeeting;
    }

    public Venue getVenueOfMeeting() {
        return venueOfMeeting;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Game getGame() {
        return game;
    }

    public Meeting toDomain() {
        return new Meeting(dateOfTheMeeting, venueOfMeeting, players, game);
    }

    public static MeetingDto fromDomain(Meeting meeting) {
        return new MeetingDto(meeting.getMeetingId(),
                meeting.getDateOfTheMeeting(),
                meeting.getVenueOfMeeting(),
                meeting.getPlayers(),
                meeting.getGame());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingDto that = (MeetingDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(dateOfTheMeeting, that.dateOfTheMeeting) &&
                Objects.equals(venueOfMeeting, that.venueOfMeeting) &&
                Objects.equals(players, that.players) &&
                Objects.equals(game, that.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfTheMeeting, venueOfMeeting, players, game);
    }

    @Override
    public String toString() {
        return "MeetingDto{" +
                "id=" + id +
                ", dateOfTheMeeting='" + dateOfTheMeeting + '\'' +
                ", venueOfMeeting=" + venueOfMeeting +
                ", players=" + players +
                ", game=" + game +
                '}';
    }
}
