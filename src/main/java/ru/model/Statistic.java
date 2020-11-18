package ru.model;

import java.time.LocalDateTime;

public class Statistic extends AbstractBaseEntity{
    private Double temperature;
    private LocalDateTime date;

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

    @Override
    public String toString() {
        return "Statistic{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", date=" + date +
                '}';
    }
}
