package epicode.it.DAO;

import epicode.it.entity.athletics_competition.AthleticsCompetition;
import epicode.it.entity.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AthleticsCompetitionDAO {
    private EntityManager em;

    public void save(AthleticsCompetition competition) {
        em.getTransaction().begin();
        em.persist(competition);
        em.getTransaction().commit();
    }

    public AthleticsCompetition findById(Long id) {
        return em.find(AthleticsCompetition.class, id);
    }

    public List<AthleticsCompetition> findAll() {
        return this.em.createNamedQuery("findAll_AthleticsCompetition", AthleticsCompetition.class).getResultList();
    }

    public void update(AthleticsCompetition competition) {
        em.getTransaction().begin();
        em.merge(competition);
        em.getTransaction().commit();
    }

    public void delete(AthleticsCompetition competition) {
        em.getTransaction().begin();
        em.remove(competition);
        em.getTransaction().commit();
    }

    public List<AthleticsCompetition> getByWinner(Person winner) {
        return this.em.createNamedQuery("groupByWinner", AthleticsCompetition.class)
                .setParameter("winner", winner).getResultList();
    }

    public List<AthleticsCompetition> getByAthlete(Person athlete) {
        return this.em.createNamedQuery("findByAthlet", AthleticsCompetition.class)
                .setParameter("athlete", athlete).getResultList();
    }

}