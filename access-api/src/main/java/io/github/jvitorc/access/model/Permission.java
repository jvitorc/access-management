package io.github.jvitorc.access.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy="permissions")
    private List<Role> roles;

}
