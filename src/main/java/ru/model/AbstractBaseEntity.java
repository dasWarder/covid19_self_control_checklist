package ru.model;

import java.util.Objects;

public abstract class AbstractBaseEntity {
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
