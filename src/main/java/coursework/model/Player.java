package coursework.model;

import coursework.model.enums.HealthStatus;
import coursework.model.enums.Status;
import coursework.validation.EnumNamePattern;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.lang.annotation.ElementType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    @Column(name = "firstName")
    private String firstName;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotNull
    @Pattern(regexp = "[a-zA-Z]*")
    @Column(name = "secondName")
    private String secondName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @EnumNamePattern(regexp = "Fine|OK|Bad", message = "Must be Fine|OK|Bad")
    @Column(name = "status")
    private Status status;

    @EnumNamePattern(regexp = "Healthy|Well|Injured", message = "Must be Healthy|Well|Injured")
    @Column(name = "healthStatus")
    private HealthStatus healthStatus;

    @Min(value = 0, message = "Salary should be greater than 0")
    @Column(name = "salary")
    private BigDecimal salary;

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
