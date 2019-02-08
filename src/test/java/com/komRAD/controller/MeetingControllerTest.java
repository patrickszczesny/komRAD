package com.komRAD.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.komRAD.KomRadApplication;
import com.komRAD.enums.GameTypes;
import com.komRAD.model.Game;
import com.komRAD.model.Meeting;
import com.komRAD.model.Player;
import com.komRAD.model.Venue;
import com.komRAD.service.MeetingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetingController.class)
@ContextConfiguration(classes = {KomRadApplication.class})
public class MeetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetingRepository meetingRepository;

    public static final MediaType APPLICATION_JSON_UTF8 =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    Charset.forName("utf8"));

    @Test
    public void shouldAddMeetingWithASetOfOnePlayer() throws Exception {

        // given
        Player onlyPlayer = new Player("Jan", "xxx");
        Set<Player> setOfPlayers = new HashSet<>();
        setOfPlayers.add(onlyPlayer);
        Venue palceOfGameSession = new Venue("Grajsfera", "Ulica Jakaś 3/8", "Toruń", "87-100", "Polska");
        Game choosenGame = new Game("Pierwsi Marsjanie: Przygody na Czerwonej Planecie", "Ignacy Trzewiczek", 4, GameTypes.EURO);
        Meeting meeting = new Meeting("08.02.2019 20:00:00", palceOfGameSession, setOfPlayers, choosenGame);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(meeting);


        given(meetingRepository.save(meeting)).willReturn(meeting);
        // when
        mockMvc.perform(post("/meeting").contentType(APPLICATION_JSON_UTF8).content(requestJson))
                //then
                .andExpect(status().isCreated());
        then(meetingRepository).should().save(meeting);
    }
}