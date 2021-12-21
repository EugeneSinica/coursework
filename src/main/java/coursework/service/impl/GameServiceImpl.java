package coursework.service.impl;

import coursework.dao.GameDao;
import coursework.model.Game;
import coursework.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameDao gameDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public void save(Game game) {
        gameDao.save(game);
    }

    @Override
    public Game get(Long id) {
        return gameDao.get(id).get();
    }

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }

    @Override
    public void update(Long id, Game game) {
        gameDao.update(id, game);
    }

    @Override
    public void delete(Long id) {
        gameDao.delete(id);
    }
}
