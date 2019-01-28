package com.komRAD.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "venues")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long venueId;

    private String nameOfVenues;
    private String address;
    private String city;
    private String zipCode;
    private String country;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "venues_meetings",
            joinColumns = @JoinColumn(name = "venue_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id"))
    private Set<Meeting> meetings;

    public Venue() {
    }

    public Venue(String nameOfVenues, String address, String city, String zipCode, String country) {
        this.nameOfVenues = nameOfVenues;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Long getVenueId() {
        return venueId;
    }

    public String getNameOfVenues() {
        return nameOfVenues;
    }

    public void setNameOfVenues(String nameOfVenues) {
        this.nameOfVenues = nameOfVenues;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        Venue venue = (Venue) o;
        return Objects.equals(venueId, venue.venueId) &&
                Objects.equals(nameOfVenues, venue.nameOfVenues) &&
                Objects.equals(address, venue.address) &&
                Objects.equals(city, venue.city) &&
                Objects.equals(zipCode, venue.zipCode) &&
                Objects.equals(country, venue.country) &&
                Objects.equals(meetings, venue.meetings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueId, nameOfVenues, address, city, zipCode, country, meetings);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "venueId=" + venueId +
                ", nameOfVenues='" + nameOfVenues + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", meetings=" + meetings +
                '}';
    }
}
