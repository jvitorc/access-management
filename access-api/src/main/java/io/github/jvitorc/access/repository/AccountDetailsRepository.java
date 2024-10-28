package io.github.jvitorc.access.repository;

import io.github.jvitorc.access.model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer> {

        Optional<AccountDetails> findByEmail( String email);

        Optional<AccountDetails> findById( Integer id);
}
