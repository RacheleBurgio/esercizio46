package test;

import com.github.javafaker.Faker;
import epicode.it.DAO.*;
import epicode.it.entity.athletics_competition.AthleticsCompetition;
import epicode.it.entity.concert.Concert;
import epicode.it.entity.concert.Genre;
import epicode.it.entity.event.Event;
import epicode.it.entity.event.EventType;
import epicode.it.entity.location.Location;
import epicode.it.entity.partecipation.Partecipation;
import epicode.it.entity.partecipation.Status;
import epicode.it.entity.person.Person;
import epicode.it.entity.person.Sex;
import epicode.it.entity.soccer_match.SoccerMatch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainInsert {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale("it"));
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit-jpa");
        EntityManager em = emf.createEntityManager();

        EventDAO eventDAO = new EventDAO(em);
        LocationDAO locationDAO = new LocationDAO(em);
        PartecipationDAO partecipationDAO = new PartecipationDAO(em);
        PersonDAO personDAO = new PersonDAO(em);

        SoccerMatchDAO soccerMatchDAO = new SoccerMatchDAO(em);
        AthleticsCompetitionDAO athleticsCompetitionDAO = new AthleticsCompetitionDAO(em);
        ConcertDAO concertDAO = new ConcertDAO(em);

        for (int i = 0; i < 20; i++) {
            Location location = new Location();
            location.setName(faker.address().cityName());
            location.setCity(faker.address().cityName());
            locationDAO.save(location);
        }

//        for (int i = 0; i < 30; i++) {
//            Event event = new Event();
//            event.setTitle(faker.lorem().sentence(2));
//            event.setDescription(faker.lorem().sentence(10));
//            event.setEventDate(faker.date().future(20, TimeUnit.DAYS));
//            event.setEventType(EventType.PUBLIC);
//            event.setMaxPartecipants(faker.random().nextInt(100, 1000));
//            event.setLocation(locationDAO.findById(faker.random().nextInt(1, 20).longValue()));
//            eventDAO.save(event);
//        }

        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setName(faker.name().firstName());
            person.setSurname(faker.name().lastName());
            person.setBirthDate(faker.date().birthday().toString());
            person.setSex(faker.random().nextInt(1, 2) == 1 ? Sex.M : Sex.F);
            person.setEmail(faker.internet().emailAddress());
            personDAO.save(person);
        }

        for (int i = 0; i < 10; i++) {
            SoccerMatch soccerMatch = new SoccerMatch();
            soccerMatch.setHomeTeam(faker.team().name());
            soccerMatch.setAwayTeam(faker.team().name());
            soccerMatch.setScoreHomeTeam(faker.random().nextInt(0, 10));
            soccerMatch.setScoreAwayTeam(faker.random().nextInt(0, 10));
            if(soccerMatch.getScoreHomeTeam() > soccerMatch.getScoreAwayTeam()) {
                soccerMatch.setWinner(soccerMatch.getHomeTeam());
            } else if(soccerMatch.getScoreHomeTeam() < soccerMatch.getScoreAwayTeam()) {
                soccerMatch.setWinner(soccerMatch.getAwayTeam());
            } else {
                soccerMatch.setWinner(null);
            }
            soccerMatch.setTitle(faker.lorem().fixedString(5));
            soccerMatch.setDescription(faker.lorem().sentence(10));
            soccerMatch.setEventDate(faker.date().future(20, TimeUnit.DAYS));
            soccerMatch.setEventType(EventType.PUBLIC);
            soccerMatch.setMaxPartecipants(faker.random().nextInt(100, 1000));
            soccerMatch.setLocation(locationDAO.findById(faker.random().nextInt(1, 20).longValue()));
            soccerMatchDAO.save(soccerMatch);

            AthleticsCompetition athleticsCompetition = new AthleticsCompetition();
            athleticsCompetition.setTitle(faker.lorem().fixedString(5));
            athleticsCompetition.setDescription(faker.lorem().sentence(10));
            athleticsCompetition.setEventDate(faker.date().future(20, TimeUnit.DAYS));
            athleticsCompetition.setEventType(faker.random().nextInt(1,2) == 1 ? EventType.PRIVATE : EventType.PUBLIC);
            athleticsCompetition.setMaxPartecipants(faker.random().nextInt(100, 1000));
            athleticsCompetition.setLocation(locationDAO.findById(faker.random().nextInt(1, 20).longValue()));
            for (int j = 0; j < 10; j++) {
                Person person = personDAO.findById(faker.random().nextInt(1, 100).longValue());
                person.getCompetitions().add(athleticsCompetition);
                athleticsCompetition.getAthletes().add(person);
            }
            List<Person> athletes = athleticsCompetition.getAthletes().stream().toList();
            Person person = athletes.get(faker.random().nextInt(0, athletes.size() - 1));
            athleticsCompetition.setWinner(athletes.get(faker.random().nextInt(0, athletes.size() - 1)));
            athleticsCompetitionDAO.save(athleticsCompetition);

            Concert concert = new Concert();
            concert.setTitle(faker.lorem().fixedString(5));
            concert.setDescription(faker.lorem().sentence(10));
            concert.setEventDate(faker.date().future(20, TimeUnit.DAYS));
            concert.setEventType(EventType.PUBLIC);
            concert.setGenre(Genre.values()[faker.random().nextInt(0, Genre.values().length - 1)]);
            concert.setInStreaming(faker.bool().bool());
            concert.setMaxPartecipants(faker.random().nextInt(500, 1000));
            concert.setLocation(locationDAO.findById(faker.random().nextInt(1, 20).longValue()));
            concertDAO.save(concert);
        }

        for (int i = 0; i < 50; i++) {
            Partecipation partecipation = new Partecipation();
            Person person = personDAO.findById(faker.random().nextInt(1, 100).longValue());
            partecipation.setPerson(person);
            partecipation.setEvent(eventDAO.findById(faker.random().nextInt(1, 30).longValue()));
            partecipation.setStatus(faker.random().nextInt(1, 2) == 1 ? Status.CONFIRMED : Status.TO_BE_CONFIRMED);
            partecipationDAO.save(partecipation);
            person.getPartecipations().add(partecipation);
            personDAO.update(person);
        }
    }

}