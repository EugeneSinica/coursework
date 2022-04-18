package coursework.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
public class Stadium {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Min(value = 0, message = "Salary should be greater than 0")
  @Column(name = "capacity")
  private int capacity;

  @Min(value = 0, message = "Salary should be greater than 0")
  @Column(name = "priceForTicket")
  private BigDecimal priceForTicket;

  @OneToMany private List<Game> games;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public BigDecimal getPriceForTicket() {
    return priceForTicket;
  }

  public void setPriceForTicket(BigDecimal priceForTicket) {
    this.priceForTicket = priceForTicket;
  }

  public List<Game> getGames() {
    return games;
  }

  public void setGames(List<Game> games) {
    this.games = games;
  }
}
