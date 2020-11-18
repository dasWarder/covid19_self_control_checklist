package ru.model;

import javax.persistence.*;
import java.util.Objects;


@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator", sequenceName = "global_seq", initialValue = 1, allocationSize = 1)
    protected Integer id;

    protected AbstractBaseEntity() {}

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //    public int id() {
//        Assert.notNull(id, "Entity must have id");
//        return id;
//    }


    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + id;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
//            return false;
//        }
//        AbstractBaseEntity that = (AbstractBaseEntity) o;
//
//        return id != null && id.equals(that.id);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
