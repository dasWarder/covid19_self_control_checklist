package ru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "symptom")
public class Symptom extends  AbstractBaseEntity{

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(max = 250)
    private String description;

    @ManyToMany(mappedBy = "symptoms", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Statistic> statistics;


    public Symptom() {}

    public Symptom(Integer id, String description) {
        super(id);
        this.description = description;
    }

    public Symptom(String description) {
        this(null, description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(Set<Statistic> statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
