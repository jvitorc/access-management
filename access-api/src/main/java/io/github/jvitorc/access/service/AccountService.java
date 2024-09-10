package io.github.jvitorc.access.service;


import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account create(Account account) {
        return repository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }
}
