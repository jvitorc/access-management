package io.github.jvitorc.access.repository;


import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.model.AccountDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

    Account save(Account account);

    Optional<Account> findById(Integer id);

    Optional<Account> findByEmail(String email);
}
