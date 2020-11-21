package ru.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "statistic")
public class Statistic extends AbstractBaseEntity {

    @Column(name = "temperature", nullable = false)
    @NotBlank
    @Size(max = 5)
    private Double temperature;

    @Column(name = "dateTime", nullable = false)
    @NotNull
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "symptom_x_statistic",
                joinColumns = @JoinColumn(name = "statistic_id"),
                inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    private Set<Symptom> symptoms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;


    public Statistic() {}

    public Statistic(Integer id, Double temperature, LocalDateTime date) {
        super(id);
        this.temperature = temperature;
        this.date = date;
    }

    public Statistic(Double temperature, LocalDateTime date) {
        this(null, temperature, date);
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Set<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", date=" + date +
                '}';
    }
}
