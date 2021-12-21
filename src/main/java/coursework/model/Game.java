package coursework.model;

import coursework.model.enums.Result;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "Games")
public class Game {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne private Player player;

  private LocalDate dateOfGame;

  @ManyToOne private Stadium stadium;

  private int viewersCount;

  private Result result;
}
