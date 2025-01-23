package epicode.it.DAO;

import epicode.it.entity.event.Event;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EventDAO {
    private EntityManager em;

    public void save(Event event) {
        em.getTransaction().begin();
        em.persist(event);
        em.getTransaction().commit();
    }

    public Event findById(Long id) {
        return em.find(Event.class, id);
    }

    public void update(Event event) {
        em.getTransaction().begin();
        em.merge(event);
        em.getTransaction().commit();
    }

    public void delete(Event event) {
        em.getTransaction().begin();
        em.remove(event);
        em.getTransaction().commit();
    }


}