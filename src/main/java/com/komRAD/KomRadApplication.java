package com.komRAD;

import com.komRAD.enums.GameTypes;
import com.komRAD.model.Game;
import com.komRAD.model.Meeting;
import com.komRAD.model.Player;
import com.komRAD.model.Venue;
import com.komRAD.service.GameRepository;
import com.komRAD.service.MeetingRepository;
import com.komRAD.service.PlayerRepository;
import com.komRAD.service.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class KomRadApplication implements CommandLineRunner {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private VenueRepository venueRepository;

	public static void main(String[] args) {
		SpringApplication.run(KomRadApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Testing");
        //Tworzę nowego gracza
        Player onlyPlayer = new Player("Jan", "xxx");
        playerRepository.save(onlyPlayer);

        //Zapisauje do Setu w celu stworzenia meetingu
        Set<Player> setOfPlayers = new HashSet<>();
        setOfPlayers.add(onlyPlayer);

        //Tworze nowe miejsce do grania
        Venue palceOfGameSession = new Venue("Grajsfera", "Ulica Jakaś 3/8", "Toruń", "87-100", "Polska");
        venueRepository.save(palceOfGameSession);

        //Tworzę nową grę
        Game choosenGame = new Game("Pierwsi Marsjanie: Przygody na Czerwonej Planecie", "Ignacy Trzewiczek", 4, GameTypes.EURO);
        gameRepository.save(choosenGame);

        //Tworzę nowy meeting
        Meeting meeting = new Meeting("08.02.2019 20:00:00", palceOfGameSession, setOfPlayers, choosenGame);
        meetingRepository.save(meeting);

        System.out.println();
        System.out.println();
        System.out.println(playerRepository.findAll().toString());

        System.out.println();
        System.out.println();
        System.out.println(gameRepository.findAll().toString());

        System.out.println();
        System.out.println();
        System.out.println(venueRepository.findAll().toString());

        System.out.println();
        System.out.println();
        System.out.println(meetingRepository.findAll().toString());
    }

}

