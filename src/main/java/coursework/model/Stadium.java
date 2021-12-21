package coursework.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Stadium {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int capacity;

  private BigDecimal priceForTicket;

  private List<Game> games;
}
