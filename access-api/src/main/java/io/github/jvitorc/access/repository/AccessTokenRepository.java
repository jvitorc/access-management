package io.github.jvitorc.access.repository;


import io.github.jvitorc.access.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {

    @Query("SELECT t FROM AccessToken t WHERE t.token = :token AND (t.revoked = false OR t.expired = false)")
    List<AccessToken> findActiveTokensByToken(@Param("token") String token);

    Optional<AccessToken> findByToken(String token);
}
