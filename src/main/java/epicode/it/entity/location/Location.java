package epicode.it.entity.location;

import epicode.it.entity.event.Event;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NamedQuery(name = "findAll_Location", query = "SELECT a FROM Location a")
@Table(name="locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String city;

    @OneToMany (mappedBy = "location")
    private List<Event> events = new ArrayList<>();
}