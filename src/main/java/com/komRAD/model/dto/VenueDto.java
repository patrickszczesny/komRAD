package com.komRAD.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.komRAD.model.Venue;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VenueDto {

    private final Long id;
    private final String nameOfVenues;
    private final String address;
    private final String city;
    private final String zipCode;
    private final String country;

    private VenueDto(
            @JsonProperty("id") Long id,
            @JsonProperty("nameOfVenues") String nameOfVenues,
            @JsonProperty("address") String address,
            @JsonProperty("city") String city,
            @JsonProperty("zipCode") String zipCode,
            @JsonProperty("country") String country
    ) {
        this.id = id;
        this.nameOfVenues = nameOfVenues;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getNameOfVenues() {
        return nameOfVenues;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public Venue toDomain() {
        return new Venue(nameOfVenues, address, city, zipCode, country);
    }

    public static VenueDto fromDomain(Venue venue) {
        return new VenueDto(venue.getVenueId(),
                venue.getNameOfVenues(),
                venue.getAddress(),
                venue.getCity(),
                venue.getZipCode(),
                venue.getCountry());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VenueDto venueDto = (VenueDto) o;
        return Objects.equals(id, venueDto.id) &&
                Objects.equals(nameOfVenues, venueDto.nameOfVenues) &&
                Objects.equals(address, venueDto.address) &&
                Objects.equals(city, venueDto.city) &&
                Objects.equals(zipCode, venueDto.zipCode) &&
                Objects.equals(country, venueDto.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOfVenues, address, city, zipCode, country);
    }

    @Override
    public String toString() {
        return "VenueDto{" +
                "id=" + id +
                ", nameOfVenues='" + nameOfVenues + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
