package ru.model;

public class Symptom extends  AbstractBaseEntity{
    private String description;

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

    @Override
    public String toString() {
        return "Symptom{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
