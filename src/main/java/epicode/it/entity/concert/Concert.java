package epicode.it.entity.concert;

import epicode.it.entity.event.Event;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@NamedQuery(name = "findAll_Concert", query = "SELECT a FROM Concert a")
@NamedQuery(name="findInStreaming", query = "SELECT a FROM Concert a WHERE a.inStreaming = :inStreaming")
@NamedQuery(name="getByGenre", query="SELECT a FROM Concert a WHERE a.genre = :genre")
@Table(name="concerts")
public class Concert extends Event {

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name="in_streaming")
    private boolean inStreaming;

}