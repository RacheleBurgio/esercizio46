package epicode.it.DAO;

import epicode.it.entity.partecipation.Partecipation;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PartecipationDAO {
    private EntityManager em;

    public void save(Partecipation partecipation) {
        em.getTransaction().begin();
        em.persist(partecipation);
        em.getTransaction().commit();
    }

    public Partecipation findById(Long id) {
        return em.find(Partecipation.class, id);
    }

    public void update(Partecipation partecipation) {
        em.getTransaction().begin();
        em.merge(partecipation);
        em.getTransaction().commit();
    }

    public void delete(Partecipation partecipation) {
        em.getTransaction().begin();
        em.remove(partecipation);
        em.getTransaction().commit();
    }
}