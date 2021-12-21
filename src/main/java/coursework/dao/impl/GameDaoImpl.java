package coursework.dao.impl;

import coursework.dao.GameDao;
import coursework.exception.DataProcessingException;
import coursework.model.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class GameDaoImpl implements GameDao {

    private final SessionFactory factory;

    @Autowired
    public GameDaoImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Transactional
    public void save(Game game) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(game);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert game" + game, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<Game> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Game.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Game by id: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<Game> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Game ", Game.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Game from db", e);
        }
    }

    @Transactional
    public void update (Long id, Game game) {
        Session session = factory.getCurrentSession();
        Game gameToUpdate = session.get(Game.class, id);
        gameToUpdate.setDateOfGame(game.getDateOfGame());
        gameToUpdate.setResult(game.getResult());
        gameToUpdate.setStadiumId(game.getStadiumId());
        gameToUpdate.setViewersCount(game.getViewersCount());
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Game game = session.get(Game.class, id);
            session.delete(game);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete Game with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
