package io.github.jvitorc.access.repository;


import io.github.jvitorc.access.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail(String eMail);

}
