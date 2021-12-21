package coursework.dao.impl;

import coursework.dao.StadiumDao;
import coursework.exception.DataProcessingException;
import coursework.model.Stadium;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class StadiumDaoImpl implements StadiumDao {

    private final SessionFactory factory;

    @Autowired
    public StadiumDaoImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Transactional
    public void save(Stadium stadium) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(stadium);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Stadium" + stadium, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Transactional(readOnly = true)
    public Optional<Stadium> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(Stadium.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Stadium by id: " + id, e);
        }
    }

    @Transactional(readOnly = true)
    public List<Stadium> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Stadium ", Stadium.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Stadium from db", e);
        }
    }

    @Transactional
    public void update (Long id, Stadium stadium) {
        Session session = factory.getCurrentSession();
        Stadium stadiumToUpdate = session.get(Stadium.class, id);
        stadiumToUpdate.setCapacity(stadium.getCapacity());
        stadiumToUpdate.setGames(stadium.getGames());
        stadiumToUpdate.setPriceForTicket(stadium.getPriceForTicket());
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            Stadium stadium = session.get(Stadium.class, id);
            session.delete(stadium);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete Stadium with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
