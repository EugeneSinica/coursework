package coursework.service;

import coursework.dao.PlayerDao;
import coursework.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    private final PlayerDao playerDao;

    @Autowired
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public void save(Player player) {
        playerDao.save(player);
    }

    @Override
    public Player get(Long id) {
        return playerDao.get(id).get();
    }

    @Override
    public List<Player> getAll() {
        return playerDao.getAll();
    }

    @Override
    public void update(Long id, Player player) {
        playerDao.update(id, player);
    }

    @Override
    public void delete(Long id) {
        playerDao.delete(id);
    }
}
