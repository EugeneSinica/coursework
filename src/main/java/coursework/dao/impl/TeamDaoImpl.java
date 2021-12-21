package coursework.dao.impl;

import coursework.dao.TeamDao;
import coursework.exception.DataProcessingException;
import coursework.model.Player;
import coursework.model.Team;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamDaoImpl implements TeamDao {

    private final SessionFactory factory;

    @Autowired
    public TeamDaoImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Transactional
    public void save(Team team) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(team);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Team" + team, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<Team> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Team.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Team by id: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<Team> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Team ", Team.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Teams from db", e);
        }
    }

    @Transactional
    public void update (Long id, Team team) {
        Session session = factory.getCurrentSession();
        Team teamToUpdate = session.get(Team.class, id);
        teamToUpdate.setName(team.getName());
        teamToUpdate.setTeam(team.getTeam());
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Team team = session.get(Team.class, id);
            session.delete(team);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete Team with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
