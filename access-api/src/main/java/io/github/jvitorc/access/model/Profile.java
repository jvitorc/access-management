package io.github.jvitorc.access.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {


    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="rl_profile_role")
    private List<Role> roles;

    @OneToMany(mappedBy = "profile")
    @JsonBackReference
    private List<Account> accounts;

    public List<String> getAuthorities() {
        return roles.stream().map(Role::getAuthorities)
                .flatMap(List::stream)
                .toList();
    }
}
