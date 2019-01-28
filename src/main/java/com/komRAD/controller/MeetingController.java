package com.komRAD.controller;

import com.komRAD.model.Meeting;
import com.komRAD.model.dto.MeetingDto;
import com.komRAD.model.listing.MeetingListing;
import com.komRAD.service.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    public MeetingRepository meetingRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MeetingListing getAllMeetings() {
        List<Meeting> meetingsList = StreamSupport.stream(meetingRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return new MeetingListing(meetingsList, meetingsList.size());
    }

    @GetMapping(value = "/meetingById/{meetingId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MeetingDto getMeetingById(@PathVariable Long meetingId) {
        return MeetingDto.fromDomain(meetingRepository.findById(meetingId).get());
    }

    @GetMapping(value = "/meetingsByDate/{date}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MeetingListing getMeetingsByDate(
            @PathVariable String date) {
        List<Meeting> meetingsList = StreamSupport.stream(meetingRepository.findAll().spliterator(), false).filter(meeting -> meeting.getDateOfTheMeeting().equals(date)).collect(Collectors.toList());
        return new MeetingListing(meetingsList, meetingsList.size());
    }

    @GetMapping(value = "/meetingsByCity/{city}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MeetingListing getMeetingsByCity(
            @PathVariable String city) {
        List<Meeting> meetingsList = StreamSupport.stream(meetingRepository.findAll().spliterator(), false).filter(meeting -> meeting.getVenueOfMeeting().getCity().equals(city)).collect(Collectors.toList());
        return new MeetingListing(meetingsList, meetingsList.size());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addMeeting(@RequestBody MeetingDto meetingDto) {
        meetingRepository.save(meetingDto.toDomain());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllMeetings() {
        meetingRepository.deleteAll();
    }

    @DeleteMapping("/deletById/{meetingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMeetingById(@PathVariable Long meetingId) {
        meetingRepository.deleteById(meetingId);
    }

    @PutMapping(value = "update/{meetingId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    public void updatePlayerById(@PathVariable Long meetingId, @RequestBody MeetingDto meetingDto) {
        Meeting meetingToUpdate = meetingRepository.findById(meetingId).get();
        meetingToUpdate.setDateOfTheMeeting(meetingDto.toDomain().getDateOfTheMeeting());
        meetingToUpdate.setGame(meetingDto.toDomain().getGame());
        meetingToUpdate.setPlayers(meetingDto.toDomain().getPlayers());
        meetingToUpdate.setVenueOfMeeting(meetingDto.toDomain().getVenueOfMeeting());
        meetingRepository.save(meetingToUpdate);
    }

}
