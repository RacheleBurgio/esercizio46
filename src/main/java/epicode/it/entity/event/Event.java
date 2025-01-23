package epicode.it.entity.event;

import epicode.it.entity.location.Location;
import epicode.it.entity.partecipation.Partecipation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NamedQuery(name = "findAll_Event", query = "SELECT a FROM Event a")

@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;

    @Column(name="event_date")
    private Date eventDate;

    private String description;

    @Column(name="event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name="max_partecipants")
    private int maxPartecipants;

    @ManyToOne
    private Location location;

    @OneToMany(mappedBy = "event")
    private List<Partecipation> partecipationList = new ArrayList<>();

}