package coursework.service;

import coursework.model.Game;
import coursework.model.Player;

import java.util.List;

public interface GameService {
    void save(Game game);

    Game get(Long id);

    List<Game> getAll();

    void update(Long id, Game game);

    void delete(Long id);
}
