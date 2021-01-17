package ru.model;


import org.hibernate.annotations.BatchSize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity implements UserDetails {

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

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 250)
    private Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @BatchSize(size = 250)
    private Set<Statistic> statistics;

    public User() {

    }

    public User(Integer id, String name, String password, String email, boolean enabled, Date registered, Role role, Role...roles) {
        super(id);
        this.name = name;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.registered = registered;
        this.roles = EnumSet.of(role, roles);
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getPassword(), user.getEmail(), user.isEnabled(), user.getRegistered(), user.getRoles());
    }

    public User(String name, String password, String email, Role role) {
        this(null, name, password, email, true, new Date(), Role.ROLE_USER);
    }

    public User(Integer id, String name, String password, String email, Role role) {
        this(id, name, password, email, true, new Date(), Role.ROLE_USER);
    }
    public User(Integer id, String name, String email, String password, boolean enabled, Date registered, Collection<Role> roles) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }


    public String getName() {
        return name;
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

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setStatistics(Set<Statistic> statistics) {
        this.statistics = statistics;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
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
