package com.komRAD.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long meetingId;

    private String dateOfTheMeeting;

    @ManyToOne(fetch = FetchType.EAGER)
    private Venue venueOfMeeting;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "meetings")
    private Set<Player> players;

    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    public Meeting() {
    }

    public Meeting(String dateOfTheMeeting, Venue venueOfMeeting, Set<Player> players, Game game) {
        this.dateOfTheMeeting = dateOfTheMeeting;
        this.venueOfMeeting = venueOfMeeting;
        this.players = players;
        this.game = game;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public String getDateOfTheMeeting() {
        return dateOfTheMeeting;
    }

    public void setDateOfTheMeeting(String dateOfTheMeeting) {
        this.dateOfTheMeeting = dateOfTheMeeting;
    }

    public Venue getVenueOfMeeting() {
        return venueOfMeeting;
    }

    public void setVenueOfMeeting(Venue venueOfMeeting) {
        this.venueOfMeeting = venueOfMeeting;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(meetingId, meeting.meetingId) &&
                Objects.equals(dateOfTheMeeting, meeting.dateOfTheMeeting) &&
                Objects.equals(venueOfMeeting, meeting.venueOfMeeting) &&
                Objects.equals(players, meeting.players) &&
                Objects.equals(game, meeting.game);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId, dateOfTheMeeting, venueOfMeeting, players, game);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", dateOfTheMeeting='" + dateOfTheMeeting + '\'' +
                ", venueOfMeeting=" + venueOfMeeting +
                ", players=" + players +
                ", game=" + game +
                '}';
    }
}
