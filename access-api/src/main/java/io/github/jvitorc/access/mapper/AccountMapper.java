package io.github.jvitorc.access.mapper;

import io.github.jvitorc.access.dto.AccountDTO;
import io.github.jvitorc.access.model.Account;

public abstract class AccountMapper {

    public static AccountDTO toDTO(Account account) {

        return AccountDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .email(account.getEmail())
                .profile(account.getProfile())
                .build();
    }

}
