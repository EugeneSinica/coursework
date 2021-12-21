package coursework.service.impl;

import coursework.dao.StadiumDao;
import coursework.model.Game;
import coursework.model.Stadium;
import coursework.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumServiceImpl implements StadiumService {
    private final StadiumDao stadiumDao;

    @Autowired
    public StadiumServiceImpl(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    @Override
    public void save(Stadium stadium) {
        stadiumDao.save(stadium);
    }

    @Override
    public Stadium get(Long id) {
        return stadiumDao.get(id).get();
    }

    @Override
    public List<Stadium> getAll() {
        return stadiumDao.getAll();
    }

    @Override
    public void update(Long id, Stadium stadium) {
        stadiumDao.update(id, stadium);
    }

    @Override
    public void delete(Long id) {
        stadiumDao.delete(id);
    }
}
