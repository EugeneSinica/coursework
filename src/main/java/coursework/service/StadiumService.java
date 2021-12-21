package coursework.service;

import coursework.model.Game;
import coursework.model.Stadium;

import java.util.List;

public interface StadiumService {
    void save(Stadium stadium);

    Stadium get(Long id);

    List<Stadium> getAll();

    void update(Long id, Stadium stadium);

    void delete(Long id);
}
