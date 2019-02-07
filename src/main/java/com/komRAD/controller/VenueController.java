package com.komRAD.controller;

import com.komRAD.model.Venue;
import com.komRAD.model.dto.VenueDto;
import com.komRAD.model.listing.VenueListing;
import com.komRAD.service.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/venue")
public class VenueController {

    @Autowired
    public VenueRepository venueRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VenueListing getAllVenues() {
        List<Venue> venuesList = StreamSupport.stream(venueRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return new VenueListing(venuesList, venuesList.size());
    }

    @GetMapping(value = "/venueById/{venueId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VenueDto getVenueById(@PathVariable Long venueId) {
        return VenueDto.fromDomain(venueRepository.findById(venueId).get());
    }

    @GetMapping(value = "/venuesByName{venueName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VenueListing getVenueByName(@PathVariable String venueName) {
        List<Venue> venuesList = StreamSupport.stream(venueRepository.findAll().spliterator(), false).filter(venue -> venue.getNameOfVenue().equals(venueName)).collect(Collectors.toList());
        return new VenueListing(venuesList, venuesList.size());
    }

    @GetMapping(value = "/venuesByCity/{city}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VenueListing getVenueByCity(@PathVariable String city) {
        List<Venue> venuesList = StreamSupport.stream(venueRepository.findAll().spliterator(), false).filter(venue -> venue.getCity().equals(city)).collect(Collectors.toList());
        return new VenueListing(venuesList, venuesList.size());
    }

    @GetMapping(value = "/venuesByCountry/{country}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public VenueListing getVenueByCountry(@PathVariable String country) {
        List<Venue> venuesList = StreamSupport.stream(venueRepository.findAll().spliterator(), false).filter(venue -> venue.getCountry().equals(country)).collect(Collectors.toList());
        return new VenueListing(venuesList, venuesList.size());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addVenue(@RequestBody VenueDto venueDto) {
        venueRepository.save(venueDto.toDomain());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllPlayers() {
        venueRepository.deleteAll();
    }

    @DeleteMapping("/deletById/{venueId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePLayerById(@PathVariable Long venueId) {
        venueRepository.deleteById(venueId);
    }

    @PutMapping(value = "update/{venueId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public void updatePlayerById(@PathVariable Long venueId, @RequestBody VenueDto venueDto) {
        Venue venueToUpdate = venueRepository.findById(venueId).get();
        venueToUpdate.setAddress(venueDto.toDomain().getAddress());
        venueToUpdate.setCity(venueDto.toDomain().getCity());
        venueToUpdate.setCountry(venueDto.toDomain().getCountry());
        venueToUpdate.setZipCode(venueDto.toDomain().getZipCode());
        venueToUpdate.setMeetings(venueDto.toDomain().getMeetings());
        venueToUpdate.setNameOfVenue(venueDto.toDomain().getNameOfVenue());
        venueRepository.save(venueToUpdate);
    }

}
