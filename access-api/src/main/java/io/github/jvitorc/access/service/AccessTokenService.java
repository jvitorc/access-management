package io.github.jvitorc.access.service;

import io.github.jvitorc.access.model.AccessToken;
import io.github.jvitorc.access.model.Account;
import io.github.jvitorc.access.repository.AccessTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccessTokenService {

    private AccessTokenRepository accessTokenRepository;

    public AccessToken save(String accessToken, Account account) {

        AccessToken build = AccessToken.builder()
                .token(accessToken).expired(false)
                .revoked(false).account(account).build();

        return accessTokenRepository.save(build);
    }

    public void revokeAllByAccount(Account account) {
        accessTokenRepository.revokeAllByAccount(account);
    }

    public Optional<AccessToken> findActiveToken(String bearerToken) {
        Optional<AccessToken> token = accessTokenRepository.findByToken(bearerToken);
        if (token.isEmpty()) {
            return Optional.empty();
        }

        if (token.get().isRevoked() || token.get().isExpired()) {
            return Optional.empty();
        }
        return token;

    }
}
