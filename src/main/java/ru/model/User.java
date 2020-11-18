package ru.model;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractBaseEntity{
    private String name;
    private String password;
    private String email;
    private Date registered;
    private Set<Role> roles;

    public User(Integer id, String name, String password, String email, Date registered, Role role, Role...roles) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.registered = registered;
        this.roles = EnumSet.of(role, roles);
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegistered() {
        return registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
