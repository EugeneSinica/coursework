package coursework.dao;

import coursework.exception.DataProcessingException;
import coursework.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final SessionFactory factory;

    @Autowired
    public PlayerDaoImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Transactional
    public void save(Player player) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert player" + player, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<Player> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Player.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Player by id: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<Player> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Player ", Player.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Players from db", e);
        }
    }

    @Transactional
    public void update (Long id, Player player) {
        Session session = factory.getCurrentSession();
        Player playerToUpdate = session.get(Player.class, id);
        playerToUpdate.setFirstName(player.getFirstName());
        playerToUpdate.setSecondName(player.getSecondName());
        playerToUpdate.setDateOfBirth(player.getDateOfBirth());
        playerToUpdate.setStatus(player.getStatus());
        playerToUpdate.setHealthStatus(player.getHealthStatus());
        playerToUpdate.setSalary(player.getSalary());
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Player player = session.get(Player.class, id);
            session.delete(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete player with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
