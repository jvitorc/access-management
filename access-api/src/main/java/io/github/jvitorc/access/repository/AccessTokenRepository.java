package io.github.jvitorc.access.repository;


import io.github.jvitorc.access.model.AccessToken;
import io.github.jvitorc.access.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Integer> {

    Optional<AccessToken> findByToken(String token);

    @Modifying
    @Query("update EN_ACCESS_TOKEN a set a.revoked = true where a.account = :account")
    int revokeAllByAccount(Account account);

}
