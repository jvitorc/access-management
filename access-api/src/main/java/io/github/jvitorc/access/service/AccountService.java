package io.github.jvitorc.access.service;


import io.github.jvitorc.access.dto.AccountDTO;
import io.github.jvitorc.access.dto.AccountUpdateDTO;
import io.github.jvitorc.access.dto.AccountUpdatePasswordDTO;
import io.github.jvitorc.access.dto.AccountUpdateProfileDTO;
import io.github.jvitorc.access.exception.BusinessException;
import io.github.jvitorc.access.exception.NotFoundException;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.model.AccountDetails;
import io.github.jvitorc.access.model.Profile;
import io.github.jvitorc.access.repository.AccountDetailsRepository;
import io.github.jvitorc.access.repository.AccountRepository;
import io.github.jvitorc.access.repository.ProfileRepository;
import io.github.jvitorc.access.validator.BasicValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final AccountDetailsRepository detailsRepository;
    private final ProfileRepository profileRepository;
    private final BasicValidator validator;
    private final PasswordEncoder passwordEncoder;

    public Account create(Account account) {
        return repository.save(account);
    }

    public Page<Account> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public AccountDTO findById(Integer id) {
        if (isNull(id)) {
            throw new BusinessException("id cannot be null");
        }

        AccountDetails account = detailsRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        Profile profile = profileRepository.findById(account.getProfileId()).orElse(null);

        return AccountDTO.builder()
                .id(account.getId()).email(account.getEmail()).name(account.getName())
                .authoirities(account.getPermissions()).profile(profile).build();
    }

    public void updateProfile(AccountUpdateProfileDTO dto) {
        validator.validate(dto);
        Account account = repository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("Account not found"));

        account.setProfile(Profile.builder().id(dto.getProfileId()).build());
        repository.save(account);
    }

    public void update(AccountUpdateDTO dto) {
        validator.validate(dto);

        Account account = repository.findById(dto.getId())
                .orElseThrow(() -> new BusinessException("Account not found"));

        account.setName(dto.getName());
        repository.save(account);
    }
}
