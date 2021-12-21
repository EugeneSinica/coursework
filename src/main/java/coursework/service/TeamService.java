package coursework.service;

import coursework.model.Team;
import coursework.model.Stadium;

import java.util.List;

public interface TeamService {
    void save(Team team);

    Team get(Long id);

    List<Team> getAll();

    void update(Long id, Team team);

    void delete(Long id);
}
