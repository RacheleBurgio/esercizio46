package epicode.it;

import com.github.javafaker.Faker;
import epicode.it.DAO.*;
import epicode.it.entity.athletics_competition.AthleticsCompetition;
import epicode.it.entity.concert.Concert;
import epicode.it.entity.concert.Genre;
import epicode.it.entity.person.Person;
import epicode.it.entity.soccer_match.SoccerMatch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        ConcertDAO concertDAO = new ConcertDAO(em);
        SoccerMatchDAO soccerMatchDAO = new SoccerMatchDAO(em);
        AthleticsCompetitionDAO athleticsCompetitionDAO = new AthleticsCompetitionDAO(em);
        PersonDAO personDAO = new PersonDAO(em);

        List<Concert> streamingConcerts = concertDAO.findInStreaming(true);
        streamingConcerts.forEach(System.out::println);

        List<Concert> noStreamingConcerts = concertDAO.findInStreaming(false);
        noStreamingConcerts.forEach(System.out::println);

        List<Concert> byGenre = concertDAO.getByGenre(Genre.valueOf("ROCK"));
        System.out.println(byGenre);

        List<SoccerMatch> soccerMatchesHome = soccerMatchDAO.findHomeWin("Varese gnomes");
        System.out.println("Varese gnomes wins (home): " + soccerMatchesHome.size());

        List<SoccerMatch> soccerMatchesAway = soccerMatchDAO.findAwayWin("Rieti sons");
        System.out.println("Rieti sons wins (away): " + soccerMatchesAway.size());

        Person person = personDAO.findById(39L);

        List<AthleticsCompetition> byWinner = athleticsCompetitionDAO.getByWinner(person);
        System.out.println("Find by winner 39L" + byWinner);

        Person athlet = personDAO.findById(28L);
        List<AthleticsCompetition> byAthlete = athleticsCompetitionDAO.getByAthlete(athlet);
        System.out.println("Find by athlet 28L" + byAthlete);


    }
}