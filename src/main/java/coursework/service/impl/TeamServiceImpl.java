package coursework.service.impl;

import coursework.dao.TeamDao;
import coursework.model.Team;
import coursework.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamDao teamDao;

    @Autowired
    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public void save(Team team) {
        teamDao.save(team);
    }

    @Override
    public Team get(Long id) {
        return teamDao.get(id).get();
    }

    @Override
    public List<Team> getAll() {
        return teamDao.getAll();
    }

    @Override
    public void update(Long id, Team team) {
        teamDao.update(id, team);
    }

    @Override
    public void delete(Long id) {
        teamDao.delete(id);
    }
}
