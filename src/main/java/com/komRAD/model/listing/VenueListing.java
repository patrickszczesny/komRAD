package com.komRAD.model.listing;

import com.komRAD.model.Venue;

import java.util.List;

public class VenueListing {

    private final List<Venue> venues;
    private final Integer numberOfVenues;

    public VenueListing(List<Venue> venuesList, int venuesListSize) {
        this.venues = venuesList;
        this.numberOfVenues = venuesListSize;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public Integer getNumberOfVenues() {
        return numberOfVenues;
    }

    @Override
    public String toString() {
        return "VenuesListing{" +
                "venues=" + venues +
                ", numberOfVenues=" + numberOfVenues +
                '}';
    }
}
