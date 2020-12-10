package ru.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity{

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "email")
    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    @Column(name = "registered", nullable = false)
    @NotNull
    private Date registered;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 250)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @BatchSize(size = 250)
    @JsonIgnore
    private Set<Statistic> statistics;

    public User() {

    }

    public User(Integer id, String name, String password, String email, Date registered, Role role, Role...roles) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.registered = registered;
        this.roles = EnumSet.of(role, roles);
    }

    public User(String name, String password, String email, Role role) {
        this(null, name, password, email, new Date(), Role.USER);
    }

    public User(Integer id, String name, String password, String email, Role role) {
        this(id, name, password, email, new Date(), Role.USER);
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

    public Set<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(Set<Statistic> statistics) {
        this.statistics = statistics;
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
