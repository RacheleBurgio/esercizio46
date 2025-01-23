package epicode.it.DAO;

import epicode.it.entity.concert.Concert;
import epicode.it.entity.concert.Genre;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ConcertDAO {
    private EntityManager em;

    public void save(Concert concert) {
        em.getTransaction().begin();
        em.persist(concert);
        em.getTransaction().commit();
    }

    public Concert findById(Long id) {
        return em.find(Concert.class, id);
    }

    public void update(Concert concert) {
        em.getTransaction().begin();
        em.merge(concert);
        em.getTransaction().commit();
    }

    public void delete(Concert concert) {
        em.getTransaction().begin();
        em.remove(concert);
        em.getTransaction().commit();
    }

    public List<Concert> findInStreaming(boolean inStreaming) {
        return this.em.createNamedQuery("findInStreaming", Concert.class)
                .setParameter("inStreaming", inStreaming).getResultList();
    }

    public List<Concert> getByGenre(Genre genre) {
        return this.em.createNamedQuery("getByGenre", Concert.class).setParameter("genre", genre).getResultList();
    }
}