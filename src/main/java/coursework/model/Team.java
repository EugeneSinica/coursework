package coursework.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private List<Player> team;

  private List<Game> games;
}
