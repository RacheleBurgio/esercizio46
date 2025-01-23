package epicode.it.entity.person;

import epicode.it.entity.athletics_competition.AthleticsCompetition;
import epicode.it.entity.partecipation.Partecipation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NamedQuery(name = "findAll_Person", query = "SELECT a FROM Person a")
@Table(name="persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String birthDate;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(mappedBy = "person")
    @ToString.Exclude
    private List<Partecipation> partecipations = new ArrayList<>();

    @ManyToMany
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AthleticsCompetition> competitions = new ArrayList<>();

}