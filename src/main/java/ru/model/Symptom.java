package ru.model;

public class Symptom {
    private Integer id;
    private String description;

    public Symptom(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Symptom(String description) {
        this(null, description);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
