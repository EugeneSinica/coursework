package coursework.service;

import coursework.model.Player;

import java.util.List;

public interface PlayerService {
    void save(Player player);

    Player get(Long id);

    List<Player> getAll();

    void update(Long id, Player player);

    void delete(Long id);
}
