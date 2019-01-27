package com.komRAD.model.listing;

import com.komRAD.model.Meeting;

import java.util.List;

public class MeetingListing {

    private final List<Meeting> meetings;
    private final Integer numberOfMeetings;

    public MeetingListing(List<Meeting> meetingsList, int meetingsListSize) {
        this.meetings = meetingsList;
        this.numberOfMeetings = meetingsListSize;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public Integer getNumberOfMeetings() {
        return numberOfMeetings;
    }

    @Override
    public String toString() {
        return "MeetingListing{" +
                "meetings=" + meetings +
                ", numberOfMeetings=" + numberOfMeetings +
                '}';
    }
}
