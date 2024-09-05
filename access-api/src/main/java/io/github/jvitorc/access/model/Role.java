package io.github.jvitorc.access.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="rl_role_permission")
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<Profile> profiles;


    public List<String> getAuthorities() {
        return permissions.stream()
                .map(Permission::getName)
                .map(p -> name + "::" + p)
                .toList();
    }
}
