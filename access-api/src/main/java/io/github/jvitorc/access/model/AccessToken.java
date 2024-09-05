package io.github.jvitorc.access.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String token;

    private boolean revoked;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account account;
}
