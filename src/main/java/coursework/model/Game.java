package coursework.model;

import coursework.model.enums.Result;
import coursework.validation.EnumNamePattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateOfGame")
    private LocalDate dateOfGame;

    private Long stadiumId;

    @Min(value = 0, message = "Salary should be greater than 0")
    @Column(name = "viewersCount")
    private int viewersCount;

    @EnumNamePattern(regexp = "Won|Lost|Draw|NotHold", message = "Must be Won|Lost|Draw|NotHold")
    @Column(name = "result")
    private Result result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(LocalDate dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    public Long getStadiumId() {
        return stadiumId;
    }

    public void setStadiumId(Long stadiumId) {
        this.stadiumId = stadiumId;
    }

    public int getViewersCount() {
        return viewersCount;
    }

    public void setViewersCount(int viewersCount) {
        this.viewersCount = viewersCount;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}