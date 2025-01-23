package epicode.it.entity.athletics_competition;

import epicode.it.entity.event.Event;
import epicode.it.entity.person.Person;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "findAll_AthleticsCompetition", query = "SELECT a FROM AthleticsCompetition a")
@NamedQuery(
        name = "findByAthlet",
        query = "SELECT a FROM AthleticsCompetition a WHERE :athlete MEMBER OF a.athletes"
)
@NamedQuery(
        name = "groupByWinner",
        query = "SELECT a FROM AthleticsCompetition a WHERE a.winner = :winner"
)
@Table(name="athletics_competitions")

public class AthleticsCompetition extends Event {

    @ManyToMany(mappedBy = "competitions", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Person> athletes = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "winner_id")
    @ToString.Exclude
    private Person winner;

}